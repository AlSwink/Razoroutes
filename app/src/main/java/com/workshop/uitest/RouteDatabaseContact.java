package com.workshop.uitest;

import android.provider.BaseColumns;
/**
 * Created by Alan Swink on 4/8/2016.
 */
public final class RouteDatabaseContact {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "routes.db";
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ", ";

    private RouteDatabaseContact(){}

    public static abstract class RouteColumns implements BaseColumns{
        public static final String TABLE_NAME = "routeTable";
        public static final String COLUMN_ID = "routeID";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_COLOR = "color";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_SERV = "inService";
        public static final String COLUMN_SHAPE = "shape";

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_ID + " INTEGER" + COMMA_SEP +
                        COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_COLOR + TEXT_TYPE + COMMA_SEP +
                        COLUMN_STATUS + " INTEGER" + COMMA_SEP +
                        COLUMN_SERV + TEXT_TYPE + COMMA_SEP +
                        COLUMN_SHAPE + TEXT_TYPE + ")";

        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
