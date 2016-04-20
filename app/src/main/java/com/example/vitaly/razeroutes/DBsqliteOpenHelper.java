package com.example.vitaly.razeroutes;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by borodin on 4/20/2016.
 */
public class DBsqliteOpenHelper extends SQLiteOpenHelper
{
    public DBsqliteOpenHelper(Context context)
    {
        super(context, DBContract.DATABASE_NAME, null, DBContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
