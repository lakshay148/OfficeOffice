package com.truedev.officeoffice.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.truedev.officeoffice.Model.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dipanshugarg on 17/5/16.
 */
public class DailyTaskDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DailyTaskdb";
    public static final String TABLE_TASK = "DailyTask";
    public static final String ID = "_id";
    public static final String TABLE_ADD_MODEL = "AddModelFragment";
    public static final String TABLE_EMPLOYEE = "EmployeeFragment";
    public static final String TABLE_ADDDOMAIN = "DomainFragment";
    public static final String Task = "task";
    public static final String Name = "name";
    public static final String EID = "eID";
    public static final String Role = "role";
    public static final String Domain = "domain";
    public static final String Password = "password";

    public static final String Date = "date";
    public static final String PROJECT = "project";
    public static final String PROJECT_TABLE = "ProjectTable";
    public static final String PROJECT_NAME_FIELD = "ProjectName";
    private static final int DATABASE_VERSION = 2;
    public static final String TAG = DailyTaskDB.class.getSimpleName();
    private static final String TABLE_USERdETAIL = "userdetail";
    private static final String TABLE_ADDROLE = "addRole";
    private static final String _ID = "_id";
    private static final String NAME = "name";
    private static final String EMPNNAME = "empname";
    private String _CHK_VALUES = "checkbox_value";
    private static DailyTaskDB mDbHelper;



    public DailyTaskDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_Emp_ADD_MODEL_TABLE = "create table " + TABLE_ADD_MODEL +
                "( " + "ID" + " integer primary key autoincrement," + "EMPID text,OWNER text ,DOMAIN text); ";

        String addEmployee = "CREATE TABLE " + TABLE_EMPLOYEE +
                "( " + "ID" + " integer primary key autoincrement," + "EMPNAME text ,EMPID text,PASSWORD text ,ROLE text); ";

        String addDomain = "CREATE TABLE " + TABLE_ADDDOMAIN +
                "( " + "ID" + " integer primary key autoincrement," + "DOMAINNAME text); ";

        String task = " CREATE TABLE " + TABLE_TASK + " ( " + ID + "  INTEGER PRIMARY KEY autoincrement , " + Task + " BLOB, " +
                Date +" Text, "+  PROJECT +" Text "+")";


        String CREATE_USERDETAIL_TABLE = "CREATE TABLE " + TABLE_USERdETAIL +
                "(" +
                _ID + " INTEGER PRIMARY KEY ," +
                NAME + " TEXT " +
                ")";

        String CREATE_ADDROLE = "CREATE TABLE " + TABLE_ADDROLE +
                "(" +
                _ID + " INTEGER PRIMARY KEY ," +
                EMPNNAME + " TEXT, " +
                _CHK_VALUES + " TEXT NOT NULL " +
                ")";
        String project = "CREATE TABLE " + PROJECT_TABLE + " ( " + ID + " Integer PRIMARY KEY autoincrement , " +
                PROJECT_NAME_FIELD + " TEXT " + " ) ";

        db.execSQL(CREATE_ADDROLE);
        db.execSQL(CREATE_USERDETAIL_TABLE);
        db.execSQL(task);
        db.execSQL(project);
        db.execSQL(CREATE_Emp_ADD_MODEL_TABLE);
        db.execSQL(addEmployee);
        db.execSQL(addDomain);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS "+ PROJECT_TABLE);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADD_MODEL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDDOMAIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERdETAIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDROLE);

    }

}
