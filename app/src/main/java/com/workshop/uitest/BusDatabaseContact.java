package com.workshop.uitest;

import android.provider.BaseColumns;

/**
 * Created by Alan Swink on 4/23/2016.
 */
public class BusDatabaseContact {
    public static final int     DATABASE_VERSION    = 1;
    public static final String  DATABASE_NAME       = "buses.db";
    public static final String  TEXT_TYPE           = " TEXT";
    public static final String  COMMA_SEP           =", ";

    private BusDatabaseContact(){}

    //
    // Marker table
    //
    public static abstract class BusColumns implements BaseColumns {
        public static final String TABLE_NAME = "busTable";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_LAT = "latitude";
        public static final String COLUMN_LNG = "longitude";
        public static final String COLUMN_DATE = "CreateDate";

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                        COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
                        COLUMN_DESC + TEXT_TYPE + COMMA_SEP +
                        COLUMN_LAT + " DOUBLE NOT NULL" + COMMA_SEP +
                        COLUMN_LNG + " DOUBLE NOT NULL" + COMMA_SEP +
                        COLUMN_DATE + TEXT_TYPE + ")";
        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
