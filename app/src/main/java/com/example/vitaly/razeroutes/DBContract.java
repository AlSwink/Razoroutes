package com.example.vitaly.razeroutes;

import android.provider.BaseColumns;

/**
 * Created by borodin on 4/20/2016.
 */
public class DBContract
{
    public static final int DATABASE_VERSION    = 1;
    public static final String DATABASE_NAME    = "razerbuses.db";
    public static final String TEXT_TYPE           = " TEXT";
    public static final String COMMA_SEP           = ", ";

    // just make it uninstantiable
    private DBContract() {}


    public static abstract class DBColumns implements BaseColumns
    {
        // Field Names:
        public static final String TABLE_NAME     = "";
        public static final String COLUMN_ID      = "_id";
        // need add the colums are not ready yet
        public static final String COLUMN_1  = "";
        public static final String COLUMN_2  = "";
        public static final String COLUMN_3  = "";

        // All Names:
        public static final String[] ALL_COLUMNS = new String[] {COLUMN_ID, COLUMN_1, COLUMN_2, COLUMN_3};

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_1 + TEXT_TYPE + COMMA_SEP +
                        COLUMN_2 + TEXT_TYPE + COMMA_SEP +
                        COLUMN_3 + TEXT_TYPE + ")";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
