package com.truedev.officeoffice.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.truedev.officeoffice.CommonUtils;
import com.truedev.officeoffice.DBFunctions;
import com.truedev.officeoffice.Database.DailyTaskDB;
import com.truedev.officeoffice.R;

/**
 * Created by dipanshugarg on 20/5/16.
 */
public class ShowAllTask extends Fragment {

    Context context;
    TextView date,project,task;

    public ShowAllTask(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_all_task_layout,container,false);
        date = (TextView)view.findViewById(R.id.date);
        project = (TextView)view.findViewById(R.id.project);
        task = (TextView)view.findViewById(R.id.project);

        DBFunctions dailyTaskDB = new DBFunctions();
        dailyTaskDB.getAllRecords(CommonUtils.getCurrentDate());
        return view;
    }
}
