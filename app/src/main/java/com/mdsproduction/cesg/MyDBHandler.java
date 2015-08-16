package com.mdsproduction.cesg;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper {

    public static final String TABLE_CESG = "CESG";


    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_province = "province";
    public static final String COLUMN_2004 = "four";
    public static final String COLUMN_2005 = "five";
    public static final String COLUMN_2006 = "six";
    public static final String COLUMN_2007 = "seven";
    public static final String COLUMN_2008 = "eight";
    public static final String COLUMN_2009 = "nine";
    public static final String COLUMN_2010 = "ten";
    public static final String COLUMN_2011 = "eleven";
    public static final String COLUMN_2012 = "twelve";
    public static final String COLUMN_2013 = "thirteen";
    public static final String COLUMN_2014 = "fourteen";

    private static final String DATABASE_NAME = "cesg.db";
    private static final int DATABASE_VERSION = 1;

    private static String DATABASE_CREATE = "create table " + TABLE_CESG +
            "(" + COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_province + " text not null, " +
            COLUMN_2004 + " text not null, " +
            COLUMN_2005 + " text not null, " +
            COLUMN_2006 + " text not null, " +
            COLUMN_2007 + " text not null, " +
            COLUMN_2008 + " text not null, " +
            COLUMN_2009 + " text not null, " +
            COLUMN_2010 + " text not null, " +
            COLUMN_2011 + " text not null, " +
            COLUMN_2012 + " text not null, " +
            COLUMN_2013 + " text not null, " +
            COLUMN_2014 + " text not null);";




    public MyDBHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("TAG", "In My Handler");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            Log.d("TAG", "In OnCreate Table");
            db.execSQL(DATABASE_CREATE);
        } catch (SQLException e) {
            Log.d("TAG", "In OnCreate Table Exception");
            // TODO
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Log.d("TAG", "In OnUpgrade Table");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CESG);
            onCreate(db);
        } catch (SQLException e) {
            Log.d("TAG", "In OnUpgrade Table Exception");
            // TODO
        }
    }
}
