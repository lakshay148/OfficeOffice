package com.truedev.officeoffice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.truedev.officeoffice.Database.DailyTaskDB;
import com.truedev.officeoffice.Model.ProjectModel;
import com.truedev.officeoffice.Model.UserData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dipanshugarg on 19/5/16.
 */
public class DBFunctions {

    ProjectModel projectModel = new ProjectModel();

    public long insertTask(ArrayList<String> taskList, String date,String project) {
        SQLiteDatabase db = ApplicationController.getTasksDB(true);
        long success = 0;
        try{
            db.beginTransaction();
            ContentValues values = new ContentValues();

            values.put(DailyTaskDB.Task,CommonUtils.serialize(taskList));
            values.put(DailyTaskDB.Date,date);
            values.put(DailyTaskDB.PROJECT,project);
            success = db.insert(DailyTaskDB.TABLE_TASK, null,values);


            db.setTransactionSuccessful();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        return success;
    }

   /* public ProjectModel getAllRecords() {
        SQLiteDatabase sqLiteDatabase = ApplicationController.getTasksDB(false);
        ArrayList<String> dateFromDatabase = new ArrayList();
        ArrayList<String> projectFromDatabase = new ArrayList();

        try {
            Cursor cursor = sqLiteDatabase.query(true,DailyTaskDB.TABLE_TASK,new String[]{DailyTaskDB.Date}, null,null,null,null,null,null);

            Cursor cursor2 = sqLiteDatabase.query(true,DailyTaskDB.TABLE_TASK,new String[]{DailyTaskDB.PROJECT}, null,null,null,null,null,null);
            if(cursor!=null) {
                cursor.moveToFirst();
                do{
                    String date = cursor.getString(cursor.getColumnIndex(DailyTaskDB.Date));
                    dateFromDatabase.add(date);
                }
                while (cursor.moveToNext());
                Log.e("Date", dateFromDatabase+"");
                projectModel.setDate(dateFromDatabase);

            }

            if(cursor2!=null) {
                cursor2.moveToFirst();
                do{
                    String Project = cursor2.getString(cursor2.getColumnIndex(DailyTaskDB.PROJECT));
                    projectFromDatabase.add(Project);
                }
                while (cursor2.moveToNext());
                Log.e("Project",projectFromDatabase+"");
                projectModel.setProject(projectFromDatabase);

            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return projectModel;

       *//* getAllProjects(dateFromDatabase, projectFromDatabase);*//*
      *//*  Cursor cursor = sqLiteDatabase.query(DailyTaskDB.TABLE_TASK,new String[]{DailyTaskDB.Task,DailyTaskDB.Date}, DailyTaskDB.Date+" =? ",
                new String[]{String.valueOf(date)},null,null,null);*//*


    }*/

    public ArrayList<ProjectModel> getAllTask() {

        Cursor cursor;
        ArrayList<ProjectModel> arrayList = new ArrayList<>();
        ProjectModel projectModel;
        SQLiteDatabase sqLiteDatabase = ApplicationController.getTasksDB(false);
        try {
            cursor = sqLiteDatabase.query(true,DailyTaskDB.TABLE_TASK,
                    new String[]{DailyTaskDB.Task,DailyTaskDB.Date,DailyTaskDB.PROJECT}, null ,null,null,null,null,null);

            if(cursor!=null) {
                cursor.moveToFirst();
                do{
                    projectModel = new ProjectModel();
                    projectModel.setDate(cursor.getString(cursor.getColumnIndex(DailyTaskDB.Date)));
                    projectModel.setProject(cursor.getString(cursor.getColumnIndex(DailyTaskDB.PROJECT)));
                    projectModel.setTask((ArrayList<String>)CommonUtils.deserialize(cursor.getBlob(cursor.getColumnIndex(DailyTaskDB.Task))));
                    arrayList.add(projectModel);

                }
                while (cursor.moveToNext());

            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return arrayList;
    }


    public void insertInProjectTable(ArrayList<String> strings){

        SQLiteDatabase db = ApplicationController.getTasksDB(true);

        try{
            db.beginTransaction();

            for (int i=0; i<strings.size(); i++){
                ContentValues values = new ContentValues();
                values.put(DailyTaskDB.PROJECT_NAME_FIELD,strings.get(i));
                Log.d("Dipanshu", strings.get(i));
                float d= db.insert(DailyTaskDB.PROJECT_TABLE, null,values);
                Log.d("Insert Successfull ", d+"");

            }
            db.setTransactionSuccessful();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
    }



    public void insertEntryADDModel( String empID,String domain, String role) {
        SQLiteDatabase db = ApplicationController.getTasksDB(true);

        try {
            db.beginTransaction();

        }catch (SQLException e){
            e.printStackTrace();
            ContentValues newValues = new ContentValues();
            newValues.put("EmpID", empID);
            newValues.put("Owner", domain);
            newValues.put("ROLE", role);
            db.insert(DailyTaskDB.TABLE_ADD_MODEL, null, newValues);
        }
        finally {
            db.endTransaction();
        }
    }


    public void insertEntry(String empName, String owner, String empID, String domain) {
        SQLiteDatabase db = ApplicationController.getTasksDB(true);
        try {
            ContentValues newValues = new ContentValues();
            newValues.put("EMPNAME", empName);
            newValues.put("EmpID", empID);
            newValues.put("PASSWORD", owner);
            newValues.put("ROLE", domain);
            db.insert(DailyTaskDB.TABLE_EMPLOYEE, null, newValues);

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
        }

    }

    public void insertEntryAddDomain(String addDomain) {
        SQLiteDatabase db = ApplicationController.getTasksDB(true);
        try {
            ContentValues newValues = new ContentValues();
            newValues.put("DOMAINNAME", addDomain);
            db.insert(DailyTaskDB.TABLE_ADDDOMAIN, null, newValues);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
    }


    public static void deleteRow(String name) {
        SQLiteDatabase db = ApplicationController.getTasksDB(true);
        try {
            db.beginTransaction();
            db.execSQL("delete from " + DailyTaskDB.TABLE_USERdETAIL + " where name ='" + name + "'");
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.d(DailyTaskDB.TAG, "Error while trying to delete  users detail");
        } finally {
            db.endTransaction();
        }


    }

    public static List<UserData> getAllUser() {

        List<UserData> usersdetail = new ArrayList<>();

        String USER_DETAIL_SELECT_QUERY = "SELECT * FROM " + DailyTaskDB.TABLE_USERdETAIL;

        SQLiteDatabase db = ApplicationController.getTasksDB(false);
        Cursor cursor = db.rawQuery(USER_DETAIL_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    UserData userData = new UserData();
                    userData.setName(cursor.getString(cursor.getColumnIndex(DailyTaskDB.NAME)));
                    usersdetail.add(userData);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(DailyTaskDB.TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return usersdetail;

    }


    public static void insertUserDetail(UserData userData) {

        SQLiteDatabase db = ApplicationController.getTasksDB(true);

        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(DailyTaskDB.NAME, userData.name);

            db.insertOrThrow(DailyTaskDB.TABLE_USERdETAIL, null, values);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d(DailyTaskDB.TAG, "Error while trying to add post to database");
        } finally {

            db.endTransaction();
        }


    }


    public static synchronized DailyTaskDB getInstance(Context context) {

        if (DailyTaskDB.mDbHelper == null) {
            DailyTaskDB.mDbHelper = new DailyTaskDB(context.getApplicationContext());
        }
        return DailyTaskDB.mDbHelper;
    }




}
