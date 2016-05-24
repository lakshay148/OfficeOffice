package com.truedev.officeoffice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.truedev.officeoffice.R;

import java.util.ArrayList;

/**
 * Created by dipanshugarg on 24/5/16.
 */
public class AddTaskListAdapter extends BaseAdapter {

    ArrayList <String> task;
    Context context;
    AddTaskListAdapter(Context context , ArrayList<String> taskList){
        this.context = context;
        this.task = taskList;
    }
    @Override
    public int getCount() {
        if(task==null)
            return 0;
        return task.size();
    }

    @Override
    public Object getItem(int position) {
        return task.get(position);
    }

    @Override
    public long getItemId(int position) {
        return task.indexOf(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.project_layout, parent, false);
        TextView textView = (TextView)view.findViewById(R.id.project);
        textView.setText(task.get(position));
        return view;
    }
}
