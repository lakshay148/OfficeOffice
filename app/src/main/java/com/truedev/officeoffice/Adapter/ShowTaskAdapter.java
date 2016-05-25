package com.truedev.officeoffice.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.truedev.officeoffice.Model.ProjectModel;
import com.truedev.officeoffice.R;

import java.util.ArrayList;

/**
 * Created by dipanshugarg on 24/5/16.
 */
public class ShowTaskAdapter extends BaseAdapter {

    ArrayList<ProjectModel> projectModel;
    Context context;
    AddTaskListAdapter addTaskListAdapter;

    public ShowTaskAdapter(Context context, ArrayList<ProjectModel> projectModel) {
        this.context = context;
        this.projectModel = projectModel;
    }

    @Override
    public int getCount() {
        return projectModel.size();
    }

    @Override
    public Object getItem(int position) {
        return projectModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return projectModel.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.show_all_task_layout, parent, false);
        TextView project = (TextView) view.findViewById(R.id.pro);
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView task_list_txt = (TextView)view.findViewById(R.id.task_list_txt);
        ListView task = (ListView) view.findViewById(R.id.task_list);
        ProjectModel projectModel = (ProjectModel) getItem(position);
        project.setText("Project : "+ projectModel.getProject());
        date.setText("Date  : " +projectModel.getDate());

        String task_txt= "";
        int i =0;
        while (i<projectModel.getTask().size()){
            int positionOfTask = i+1;
            task_txt = task_txt+ positionOfTask +". " + projectModel.getTask().get(i)+"\n";
            i++;
        }

        Log.e("Dipanshu", task_txt+"");
        task_list_txt.setText(task_txt);

        addTaskListAdapter = new AddTaskListAdapter(context , projectModel.getTask());
        task.setAdapter(addTaskListAdapter);
        return view;
    }
}
