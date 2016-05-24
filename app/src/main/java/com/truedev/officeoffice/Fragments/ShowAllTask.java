package com.truedev.officeoffice.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.truedev.officeoffice.Adapter.ShowTaskAdapter;
import com.truedev.officeoffice.CommonUtils;
import com.truedev.officeoffice.DBFunctions;
import com.truedev.officeoffice.Database.DailyTaskDB;
import com.truedev.officeoffice.Model.ProjectModel;
import com.truedev.officeoffice.R;

import java.util.ArrayList;

/**
 * Created by dipanshugarg on 20/5/16.
 */
public class ShowAllTask extends Fragment {

    Context context;
    ListView listView;
    ShowTaskAdapter showTaskAdapter;
    TextView date,project,task;
    public ShowAllTask(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.show_all_task_list,container,false);

        listView = (ListView)view.findViewById(R.id.data_show);

        DBFunctions dbFunctions = new DBFunctions();
        ArrayList<ProjectModel> projectModel = dbFunctions.getAllTask();
        showTaskAdapter = new ShowTaskAdapter(context, projectModel );
        listView.setAdapter(showTaskAdapter);
       /* date = (TextView)view.findViewById(R.id.date);
        project = (TextView)view.findViewById(R.id.project);
        task = (TextView)view.findViewById(R.id.project);

        DBFunctions dailyTaskDB = new DBFunctions();
        dailyTaskDB.getAllRecords();*/
        return view;
    }
}
