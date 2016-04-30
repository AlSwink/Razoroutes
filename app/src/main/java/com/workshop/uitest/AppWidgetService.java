package com.workshop.uitest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AppWidgetService extends Service {

    String tag = "AppWidgetService";
    private CountDownTimer countDownTimer;
    private ArrayList<String> blueWeekdaySchedule;
    private ArrayList<String> blueSaturdaySchedule;
    private ArrayList<String> redWeekdaySchedule;
    private ArrayList<String> redSaturdaySchedule;

    public AppWidgetService() {}

    @Override
    public int onStartCommand( Intent intent, int flags, int startId) {

        //Log.v(tag, "ONSTARTCOMMAND");
        BusSchedule busSchedule = new BusSchedule();
        blueWeekdaySchedule = busSchedule.getBlueWeekdaySchedule();
        blueSaturdaySchedule= busSchedule.getBlueSaturdaySchedule();
        redWeekdaySchedule = busSchedule.getRedWeekdaySchedule();
        redSaturdaySchedule= busSchedule.getRedSaturdaySchedule();

        final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
                .getApplicationContext());

        final RemoteViews remoteViews = new RemoteViews(this
                .getApplicationContext().getPackageName(),
                R.layout.app_widget);

        for (final int widgetId : intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS)) {
            Timer(appWidgetManager,remoteViews,widgetId);
        }

        startServiceForeground(startId);
        return START_NOT_STICKY;
    }


    private void Timer(final AppWidgetManager appWidgetManager, final RemoteViews remoteViews, final int widgetId){

        int timeToNextBus = 1000000;
        countDownTimer = new CountDownTimer(timeToNextBus, 1000) {
            public void onTick(long millisUntilFinished) {

                //Log.v("TAG", "EVERYTHING AGAIN");

                //GET CURRENT TIME
                Date date = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(date);

                int currentSecond = c.get(Calendar.SECOND);
                int currentMinute = c.get(Calendar.MINUTE);
                int currentHour = c.get(Calendar.HOUR_OF_DAY);
                int currentDay = c.get(Calendar.DAY_OF_WEEK);

                //COMPARE CURRENT TIME WITH DEPARTURE TIME

                boolean blueUpComingBus = false;
                int blueDepartureTimesIndex =0 ;
                int blueDepartureHour;
                int blueDepartureMinute;

                boolean redUpComingBus = false;
                int redDepartureTimesIndex =0 ;
                int redDepartureHour;
                int redDepartureMinute;


                if (currentDay == 1){
                    remoteViews.setTextViewText(R.id.bus1, "NO BUSES ON SUNDAY") ;
                    remoteViews.setTextViewText(R.id.bus2, "" ) ;
                }
                else if (currentHour >= 22 && currentMinute >=10){
                    remoteViews.setTextViewText(R.id.bus1, "NO MORE BUSES TODAY") ;
                    remoteViews.setTextViewText(R.id.bus2, "") ;
                }
                else if (currentHour <= 5){
                    remoteViews.setTextViewText(R.id.bus1, "BUS DEPARTS AROUND") ;
                    remoteViews.setTextViewText(R.id.bus2, "7:00") ;
                }
                else if (currentDay != 7){
                    //RED WEEKDAY
                    while(!blueUpComingBus && blueDepartureTimesIndex <= blueWeekdaySchedule.size()){

                        blueDepartureHour = Integer.parseInt(blueWeekdaySchedule.get(blueDepartureTimesIndex).substring(0, blueWeekdaySchedule.get(blueDepartureTimesIndex).indexOf(":")));
                        blueDepartureMinute = Integer.parseInt(blueWeekdaySchedule.get(blueDepartureTimesIndex).substring(blueWeekdaySchedule.get(blueDepartureTimesIndex).indexOf(":") +1, blueWeekdaySchedule.get(blueDepartureTimesIndex).length()));

                        if (currentHour > blueDepartureHour || (currentHour == blueDepartureHour && currentMinute >= blueDepartureMinute)){
                            blueDepartureTimesIndex++;
                        }
                        else{
                            remoteViews.setTextViewText(R.id.bus1, Integer.toString(((blueDepartureHour-currentHour)*60) + blueDepartureMinute-currentMinute -1 ) + ":" + String.format("%02d", 59 - currentSecond) + " BLUE") ;
                            blueUpComingBus = true;
                        }
                    }
                    //RED WEEKDAY
                    while(!redUpComingBus && redDepartureTimesIndex <= redWeekdaySchedule.size() ){

                        redDepartureHour = Integer.parseInt(redWeekdaySchedule.get(redDepartureTimesIndex).substring(0, redWeekdaySchedule.get(redDepartureTimesIndex).indexOf(":")));
                        redDepartureMinute = Integer.parseInt(redWeekdaySchedule.get(redDepartureTimesIndex).substring(redWeekdaySchedule.get(redDepartureTimesIndex).indexOf(":") +1, redWeekdaySchedule.get(redDepartureTimesIndex).length()));

                        if (currentHour > redDepartureHour || (currentHour == redDepartureHour && currentMinute >= redDepartureMinute)){
                            redDepartureTimesIndex++;
                        }
                        else{
                            remoteViews.setTextViewText(R.id.bus2, Integer.toString(((redDepartureHour-currentHour)*60) + redDepartureMinute-currentMinute -1 ) + ":" + String.format("%02d", 59 - currentSecond) + " RED") ;
                            redUpComingBus = true;
                        }
                    }
                }
                else {
                    while(!blueUpComingBus && blueDepartureTimesIndex <= blueSaturdaySchedule.size() ){

                        blueDepartureHour = Integer.parseInt(blueSaturdaySchedule.get(blueDepartureTimesIndex).substring(0, blueSaturdaySchedule.get(blueDepartureTimesIndex).indexOf(":")));
                        blueDepartureMinute = Integer.parseInt(blueSaturdaySchedule.get(blueDepartureTimesIndex).substring(blueSaturdaySchedule.get(blueDepartureTimesIndex).indexOf(":") +1, blueSaturdaySchedule.get(blueDepartureTimesIndex).length()));

                        if (currentHour > blueDepartureHour || (currentHour == blueDepartureHour && currentMinute >= blueDepartureMinute)){
                            blueDepartureTimesIndex++;
                        }
                        else{
                            remoteViews.setTextViewText(R.id.bus1, Integer.toString(((blueDepartureHour-currentHour)*60) + blueDepartureMinute-currentMinute -1 ) + ":" + String.format("%02d", 59 - currentSecond) + " BLUE") ;
                            blueUpComingBus = true;
                        }
                    }
                    //RED SATURDAY
                    while(!redUpComingBus && redDepartureTimesIndex <= redSaturdaySchedule.size() ){

                        redDepartureHour = Integer.parseInt(redSaturdaySchedule.get(redDepartureTimesIndex).substring(0, redSaturdaySchedule.get(redDepartureTimesIndex).indexOf(":")));
                        redDepartureMinute = Integer.parseInt(redSaturdaySchedule.get(redDepartureTimesIndex).substring(redSaturdaySchedule.get(redDepartureTimesIndex).indexOf(":") +1, redSaturdaySchedule.get(redDepartureTimesIndex).length()));

                        if (currentHour > redDepartureHour || (currentHour == redDepartureHour && currentMinute >= redDepartureMinute)){
                            redDepartureTimesIndex++;
                        }
                        else{
                            remoteViews.setTextViewText(R.id.bus2, Integer.toString(((redDepartureHour-currentHour)*60) + redDepartureMinute-currentMinute -1 ) + ":" + String.format("%02d", 59 - currentSecond) + " RED") ;
                            redUpComingBus = true;
                        }
                    }
                }
                //Log.v("TAG", "ONTICK");
                appWidgetManager.updateAppWidget(widgetId, remoteViews);
            }
            public void onFinish() {
                //Log.v("TAG", "ONFINISH");
                start();
            }
        }.start();
    }

    @Override
    public void onDestroy(){
        //Log.v(tag, "ONDESTROY");
        countDownTimer.cancel();
        super.onDestroy();
    }

    //START OUR SERVICE IN FOREGROUND SO THAT THE WIDGET DOES NOT GET KILLED WHEN THE APP IS OPENED AND CLOSED
    public void startServiceForeground(int startId){

        Intent notificationIntent = new Intent(this, AppWidgetService.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Counter Service")
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .build();

        startForeground(startId, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        //Log.v(tag, "ONBIND");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
