package com.truedev.officeoffice;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.truedev.officeoffice.Database.DailyTaskDB;

import java.util.ArrayList;

/**
 * Created by dipanshugarg on 20/5/16.
 */
public class ApplicationController extends Application {
    public static DailyTaskDB dailyTaskDB =null;


    @Override
    public void onCreate() {
        super.onCreate();
        if (dailyTaskDB == null) {
            dailyTaskDB = new DailyTaskDB(this);
        }
    }

    public synchronized static SQLiteDatabase getTasksDB(boolean writable) {
        if (writable)
            return dailyTaskDB.getWritableDatabase();
        else
            return dailyTaskDB.getReadableDatabase();
    }

    public static void insertProject() {
        ArrayList<String> project = new ArrayList<>();
        project.add("SFA");
        project.add("Auction");
        project.add("Evaluator");
        project.add("Gcloud");
        project.add("Gcloud");
        project.add("Gcloud IOS");
        DBFunctions dbFunctions = new DBFunctions();
        dbFunctions.insertInProjectTable(project);
    }
}

