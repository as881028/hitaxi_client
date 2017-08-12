package com.hitaxi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;

/**
 * Created by DK on 2017/7/10.
 */

public class DBHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "taxi_go.db";
    private final static int DATABASE_VERSION = 9;
    protected static final String TAG = "DBHelper";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        onCreate();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dropDB(db);

        final String INIT_TABLE = "CREATE TABLE " + DBConstants.TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBConstants.TOKEN + " CHAR , " +
                DBConstants.USERID + " CHAR , " +
                DBConstants.ACCOUNT + " CHAR UNIQUE, " +
                DBConstants.PASSWORD + " CHAR);";
        db.execSQL(INIT_TABLE);


    }

    public void dropDB(SQLiteDatabase db) {
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + DBConstants.TABLE_NAME;
        db.execSQL(DROP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        dropDB(db);
        onCreate(db);

    }
}
