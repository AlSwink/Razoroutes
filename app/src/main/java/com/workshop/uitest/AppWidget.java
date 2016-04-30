package com.workshop.uitest;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AppWidget extends AppWidgetProvider {

    String tag = "APPWIDGET";

    @Override
    public void onUpdate(Context context, final AppWidgetManager appWidgetManager,final int[] appWidgetIds) {
        //Log.v(tag, "ON UPDATE");

        //CALLING SERVICE
        ComponentName thisWidget = new ComponentName(context,
                AppWidget.class);

        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        //SERVICE INTENT
        Intent intent = new Intent(context.getApplicationContext(),
                AppWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

        //UPDATE WIDGET FROM SERVICE
        //Log.v(tag, "startService " + context.getApplicationContext() );
        context.startService(intent);
    }

    @Override
    public void onEnabled(Context context) {
        //Log.v(tag, "ON ENABLE");
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds){
        //Log.v(tag, "onDeleted " + context.getApplicationContext());
        //STOP THE SERVICE ONCE THE OBJECT WIDGET IS DESTROYED
        context.stopService(new Intent(context.getApplicationContext(),
                AppWidgetService.class));
    }
    @Override
    public void onDisabled(Context context) {
        //Log.v(tag, "ON DISABLE");
    }
}

