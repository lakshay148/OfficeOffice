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

    private Context mContext;
    private ArrayList<String> mRowDatas = new ArrayList<>();
    private TextView mProject;

    public ProjectsAdapter(Context context , ArrayList<String> list) {
        this.mContext = context;
        this.mRowDatas = list;
    }
    @Override
    public int getCount() {
        return mRowDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mRowDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mRowDatas.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.project_layout,parent,false);
        mProject = (TextView)view.findViewById(R.id.project);
        mProject.setText(mRowDatas.get(position));
        return view;
    }
}
