package com.truedev.officeoffice.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by dipanshugarg on 17/5/16.
 */
public class DailyTaskDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DailyTaskdb";
    public static final String ID = "_id";

    public static final String TABLE_TASK = "DailyTask";
    public static final String TABLE_ADDDOMAIN = "DomainFragment";
    public static final String TABLE_EMPdETAIL = "empdetail";
    public static final String TABLE_MODULE = "addmodule";

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
    public static final int DATABASE_VERSION = 2;
    public static final String TAG = DailyTaskDB.class.getSimpleName();
    public static final String TABLE_USERdETAIL = "userdetail";
    public static final String TABLE_ADDROLE = "addRole";
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String ROLENAME = "rolename";
    public static final String PRIVILEGENAME = "prvlgname";
    public static final String _CHK_VALUES = "checkbox_value";
    public static DailyTaskDB mDbHelper;

    public static final String EMPNAME = "name";
    public static final String EMPID = "empid";
    public static final String EMPPASSWORD = "password";
    public static final String EMPROLE = "role";
    public static final String ADDMODULEROLE = "modulerole";
    public static final String ADDMODULEDOMAIN = "moduledomain";



    public DailyTaskDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String addDomain = "CREATE TABLE " + TABLE_ADDDOMAIN +
                "( " + "ID" + " integer primary key autoincrement," + "DOMAINNAME text); ";

        String task = " CREATE TABLE " + TABLE_TASK + " ( " + ID + "  INTEGER PRIMARY KEY autoincrement , " + Task + " BLOB, " +
                Date + " Text, " + PROJECT + " Text " + ")";


        String CREATE_USERDETAIL_TABLE = "CREATE TABLE " + TABLE_USERdETAIL +
                "(" +
                _ID + " INTEGER PRIMARY KEY ," +
                NAME + " TEXT " +
                ")";

        String project = "CREATE TABLE " + PROJECT_TABLE + " ( " + ID + " Integer PRIMARY KEY autoincrement , " +
                PROJECT_NAME_FIELD + " TEXT " + " ) ";

        String CREATE_EMPDETAIL_TABLE = "CREATE TABLE " + TABLE_EMPdETAIL +
                "(" +
                _ID + " INTEGER PRIMARY KEY ," +
                EMPID + " TEXT, " +
                EMPNAME + " TEXT, " +
                EMPPASSWORD + " TEXT, " +
                EMPROLE + " TEXT " +
                ")";
        String CREATE_ADDMODULE_TABLE = "CREATE TABLE " + TABLE_MODULE +
                "(" +
                _ID + " INTEGER PRIMARY KEY , " +
                EMPID + " TEXT, " +
                ADDMODULEDOMAIN + " TEXT, " +
                ADDMODULEROLE + " TEXT  " +
                ")";

        String CREATE_ADDROLE = "CREATE TABLE " + TABLE_ADDROLE +
                "(" +
                _ID + " INTEGER PRIMARY KEY ," +
                ROLENAME + " TEXT ," +
                PRIVILEGENAME + " TEXT " +
                ")";

        Log.e("Add Domain", addDomain);
        Log.e("Task", task);
        Log.e("CREATE_USERDETAIL_TABLE", CREATE_USERDETAIL_TABLE);
        Log.e("CREATE_ADDROLE", CREATE_ADDROLE);
        Log.e("project", project);
        Log.e("CREATE_EMPDETAIL_TABLE", CREATE_EMPDETAIL_TABLE);
        Log.e("CREATE_ADDMODULE_TABLE", CREATE_ADDMODULE_TABLE);

        db.execSQL(CREATE_ADDROLE);
        db.execSQL(CREATE_EMPDETAIL_TABLE);
        db.execSQL(CREATE_USERDETAIL_TABLE);
        db.execSQL(task);
        db.execSQL(project);
        db.execSQL(CREATE_ADDMODULE_TABLE);
        db.execSQL(addDomain);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS " + PROJECT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDDOMAIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERdETAIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDROLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPdETAIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MODULE);
        onCreate(db);
    }
}
