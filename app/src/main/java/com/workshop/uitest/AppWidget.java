package com.workshop.uitest;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidget extends AppWidgetProvider {

    private String bus1 = "Blue";
    private String bus2 = "Green";

    @Override
    public void onUpdate(Context context, final AppWidgetManager appWidgetManager,final int[] appWidgetIds) {

        /*
        // There may be multiple widgets active, so update all of them but since we only have one widget
        //we don't need it

        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
        updateAppWidget(context, appWidgetManager, appWidgetIds[0]);
        */
        Timer(context,appWidgetManager,appWidgetIds[0]);
    }


    public void Timer(Context context, final AppWidgetManager appWidgetManager,final int appWidgetIds){

        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        views.setTextViewText(R.id.bus1, "TESTING");

        CountDownTimer countDownTimer = new CountDownTimer(300000, 1000) {
            public void onTick(long millisUntilFinished) {
                views.setTextViewText(R.id.bus1, millisUntilFinished/60000+ ":" + String.format("%02d", ((millisUntilFinished/1000)%60)) +" " + bus1 ) ;
                views.setTextViewText(R.id.bus2, millisUntilFinished/60000+ ":" + String.format("%02d", ((millisUntilFinished/1000)%60)) +" " + bus2 ) ;
                appWidgetManager.updateAppWidget(appWidgetIds, views);
                Log.v("TAG", "ONTICK");
            }
            public void onFinish() {
                views.setTextViewText(R.id.bus1, "done");
                Log.v("TAG", "ONFINISH");
            }
        }.start();
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    /*
    //USED IF WE HAD MORE THAN ONE WIDGET
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        //views.setTextViewText(R.id.appwidget_text, String.valueOf("TESTTT"));

        //Chronometer
        //views.setChronometer(R.id.chronometer, SystemClock.elapsedRealtime(), "%s", true);

        //Countdown
        //Text timer = "time";
        //views.setTextViewText(R.id.timer,);

        // Instruct the widget manager to update the widget
        //appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    */
}

