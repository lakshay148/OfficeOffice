package com.truedev.officeoffice.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by dipanshugarg on 17/5/16.
 */
public class DailyTaskDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DailyTaskdb";
    public static final String TABLE_TASK = "DailyTask";
    public static final String ID = "id";
    public static final String Task = "task";
    public static final String Date = "date";
    public static final String PROJECT = "project";
    private static final int DATABASE_VERSION = 2;

    public DailyTaskDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String task = " CREATE TABLE " + TABLE_TASK + " ( " + ID + "  INTEGER PRIMARY KEY autoincrement , " + Task + " TEXT, " +
                Date +" Text, "+  PROJECT +" Text "+")";
        db.execSQL(task);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
    }


}
