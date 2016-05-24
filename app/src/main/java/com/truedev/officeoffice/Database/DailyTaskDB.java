package com.truedev.officeoffice.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
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
    public static final String TABLE_ADD_MODEL = "AddModelFragment";
    private static final String TABLE_EMPLOYEE = "EmployeeFragment";
    private static final String TABLE_ADDDOMAIN = "DomainFragment";
    public static final String ID = "id";
    public static final String Task = "task";
    public static final String Date = "date";
    public static final String PROJECT = "project";
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

        String task = " CREATE TABLE " + TABLE_TASK + " ( " + ID + "  INTEGER PRIMARY KEY autoincrement , " + Task + " TEXT, " +
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

        db.execSQL(CREATE_ADDROLE);
        db.execSQL(CREATE_USERDETAIL_TABLE);
        db.execSQL(task);
        db.execSQL(CREATE_Emp_ADD_MODEL_TABLE);
        db.execSQL(addEmployee);
        db.execSQL(addDomain);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADD_MODEL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDDOMAIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERdETAIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDROLE);

    }


    public long insertTask(ArrayList<String> data) {
        long success = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        for (int i = 0; i < data.size(); i++) {
            values.put(Task, data.get(i));
            success = db.insert(TABLE_TASK, null, values);
        }
        db.close();
        return success;
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
// Assign values for each row.
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
    public static synchronized DailyTaskDB getInstance(Context context) {

        if (mDbHelper == null) {
            mDbHelper = new DailyTaskDB(context.getApplicationContext());
        }
        return mDbHelper;
    }

    public void insertUserDetail(UserData userData) {

        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(NAME, userData.name);

            db.insertOrThrow(TABLE_USERdETAIL, null, values);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d(TAG, "Error while trying to add post to database");
        } finally {

            db.endTransaction();
        }


    }

    public List<UserData> getAllUser() {

        List<UserData> usersdetail = new ArrayList<>();

        String USER_DETAIL_SELECT_QUERY = "SELECT * FROM " + TABLE_USERdETAIL;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(USER_DETAIL_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    UserData userData = new UserData();
                    userData.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                    usersdetail.add(userData);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return usersdetail;

    }

    /*
   Delete single row from UserTable
     */
    public void deleteRow(String name) {
        SQLiteDatabase db = getWritableDatabase();


        try {
            db.beginTransaction();
            db.execSQL("delete from " + TABLE_USERdETAIL + " where name ='" + name + "'");
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.d(TAG, "Error while trying to delete  users detail");
        } finally {
            db.endTransaction();
        }


    }

    public void insertRole(String addPrefrences, ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues newValues2 = new ContentValues();
        Set<Map.Entry<String, Object>> s = values.valueSet();
        String new_val = "";
        for (Map.Entry<String, Object> entry : s) {
            new_val = values.getAsString(entry.getKey());
            newValues2.put(entry.getKey(), new_val);
            db.insert(addPrefrences, null, newValues2);

        }
    }



}
