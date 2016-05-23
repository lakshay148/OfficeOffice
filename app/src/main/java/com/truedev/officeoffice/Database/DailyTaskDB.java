package com.truedev.officeoffice.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DailyTaskDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DailyTaskdb";
    public static final String TABLE_TASK = "DailyTask";
    public static final String ID = "_id";
    public static final String TABLE_ADD_MODEL = "AddModelFragment";
    private static final String TABLE_EMPLOYEE = "EmployeeFragment";
    private static final String TABLE_ADDDOMAIN = "DomainFragment";
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

        String task = " CREATE TABLE " + TABLE_TASK + " ( " + ID + "  INTEGER PRIMARY KEY autoincrement , " + Task + " TEXT, " +
                Date +" Text, "+  PROJECT +" Text "+")";

        String project = "CREATE TABLE " + PROJECT_TABLE + " ( " + ID + " Integer PRIMARY KEY autoincrement , " +
                PROJECT_NAME_FIELD + " TEXT " + " ) ";

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

    }



    public void insertEntryADDModel( String empID,String domain, String role) {
        ContentValues newValues = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        newValues.put("EmpID", empID);
        newValues.put("Owner", domain);
        newValues.put("ROLE", role);
        db.insert(TABLE_ADD_MODEL, null, newValues);
    }

    public void insertEntry(String empName, String owner, String empID, String domain) {
        ContentValues newValues = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        newValues.put("EMPNAME", empName);
        newValues.put("EmpID", empID);
        newValues.put("PASSWORD", owner);
        newValues.put("ROLE", domain);
        db.insert(TABLE_EMPLOYEE, null, newValues);
        db.close();
    }

    public void insertEntryAddDomain(String addDomain) {
        ContentValues newValues = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        newValues.put("DOMAINNAME", addDomain);
        db.insert(TABLE_ADDDOMAIN, null, newValues);
        db.close();
    }
}
