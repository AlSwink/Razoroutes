package com.workshop.uitest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;


import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.workshop.uitest.RouteDatabaseContact.*;
import static com.workshop.uitest.RouteDatabaseContact.RouteColumns.*;

/**
 * Created by Alan Swink on 4/7/2016.
 */
public class RouteSQLiteOpenHelper extends SQLiteOpenHelper {

    public RouteSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public void init(SQLiteDatabase db) {

        db.execSQL(RouteDatabaseContact.RouteColumns.DELETE_TABLE);

        db.execSQL(RouteDatabaseContact.RouteColumns.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    public void insert(SQLiteDatabase db, Route route) {
        ContentValues values = new ContentValues();
        List<LatLng> inputArray = route.getShape();
        Gson gson = new Gson();

        String inputString= gson.toJson(inputArray);

        values.put(COLUMN_ID, route.getID());
        values.put(COLUMN_NAME, route.getName());
        values.put(COLUMN_COLOR, route.getColor());
        values.put(COLUMN_STATUS, route.getStatus());
        values.put(COLUMN_SERV, route.getInService());
        values.put(COLUMN_SHAPE, inputString);
        db.insert(TABLE_NAME, null, values);
    }
}
