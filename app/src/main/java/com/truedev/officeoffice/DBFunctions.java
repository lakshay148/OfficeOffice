package com.truedev.officeoffice;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.truedev.officeoffice.Database.DailyTaskDB;

import java.util.ArrayList;

/**
 * Created by dipanshugarg on 19/5/16.
 */
public class DBFunctions {

    public long insertTask(ArrayList<String> data, String date,String project) {
        SQLiteDatabase db = ApplicationController.getTasksDB(true);
        long success = 0;
        try{
            db.beginTransaction();
            ContentValues values = new ContentValues();
            for(int i= 0; i<data.size();i++) {
                values.put(DailyTaskDB.Task,data.get(i));
                values.put(DailyTaskDB.Date,date);
                values.put(DailyTaskDB.PROJECT,project);
                success = db.insert(DailyTaskDB.TABLE_TASK, null,values);

            }
            db.setTransactionSuccessful();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
           db.endTransaction();
        }
        return success;
    }

    public void getAllRecords(String date) {
        SQLiteDatabase sqLiteDatabase = ApplicationController.getTasksDB(false);

      /*  Cursor cursor = sqLiteDatabase.query(DailyTaskDB.TABLE_TASK,new String[]{DailyTaskDB.Task,DailyTaskDB.Date}, DailyTaskDB.Date+" =? ",
                new String[]{String.valueOf(date)},null,null,null);*/
        Cursor cursor = sqLiteDatabase.query(true,DailyTaskDB.TABLE_TASK,new String[]{DailyTaskDB.Date}, null,null,null,null,null,null);

        if(cursor!=null) {
            cursor.moveToFirst();
            do{
                Log.e("Dipanshu DataBase:",  /*cursor.getString(cursor.getColumnIndex(DailyTaskDB.Task))+*/
                        cursor.getString(cursor.getColumnIndex(DailyTaskDB.Date)) );
            }
            while (cursor.moveToNext());
        }

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


}
