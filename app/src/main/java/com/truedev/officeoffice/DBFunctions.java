package com.truedev.officeoffice;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.truedev.officeoffice.Database.DailyTaskDB;
import com.truedev.officeoffice.Model.ProjectModel;

import java.util.ArrayList;

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
}
