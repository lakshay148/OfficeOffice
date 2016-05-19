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
 * Created by dipanshugarg on 19/5/16.
 */
public class ProjectsAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> rowDatas = new ArrayList<>();

    public ProjectsAdapter(Context context , ArrayList<String> list) {
        this.context = context;
        this.rowDatas = list;
    }
    @Override
    public int getCount() {
        return rowDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return rowDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowDatas.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.project_layout,parent,false);
        TextView textView = (TextView)view.findViewById(R.id.project);
        textView.setText(rowDatas.get(position));
        return view;
    }
}
