package com.truedev.officeoffice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.truedev.officeoffice.Model.ProjectModel;
import com.truedev.officeoffice.R;

import java.util.ArrayList;

public class ShowTaskAdapter extends BaseAdapter {

    private ArrayList<ProjectModel> mProjectModel;
    private Context mContext;
    private TextView mProject,mDate,mTask_List_Txt;
    private ProjectModel projectModel;

    public ShowTaskAdapter(Context context, ArrayList<ProjectModel> projectModel) {
        this.mContext = context;
        this.mProjectModel = projectModel;
    }

    @Override
    public int getCount() {
        return mProjectModel.size();
    }

    @Override
    public Object getItem(int position) {
        return mProjectModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mProjectModel.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.show_all_task_layout, parent, false);
        mProject = (TextView) view.findViewById(R.id.pro);
        mDate = (TextView) view.findViewById(R.id.date);
        mTask_List_Txt = (TextView)view.findViewById(R.id.task_list_txt);
        projectModel = (ProjectModel) getItem(position);
        mProject.setText("Project : "+ projectModel.getProject());
        mDate.setText("Date  : " +projectModel.getDate());

        String mTask_txt= "";
        int i =0;
        while (i<projectModel.getTask().size()) {
            int positionOfTask = i+1;
            mTask_txt = mTask_txt+ positionOfTask +". " + projectModel.getTask().get(i)+"\n";
            i++;
        }

        mTask_List_Txt.setText(mTask_txt);
        return view;
    }
}
