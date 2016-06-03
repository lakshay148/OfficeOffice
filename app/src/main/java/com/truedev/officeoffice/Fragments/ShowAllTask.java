package com.truedev.officeoffice.Fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.truedev.officeoffice.Adapter.ShowTaskAdapter;
import com.truedev.officeoffice.DBFunctions;
import com.truedev.officeoffice.Model.ProjectModel;
import com.truedev.officeoffice.R;

import java.util.ArrayList;

public class ShowAllTask extends Fragment {

    private ListView mDataShowListView;
    private ShowTaskAdapter mShowTaskAdapter;
    private Context mContext;

    public static ShowAllTask newInstance(Context applicationContext) {
        ShowAllTask fragment = new ShowAllTask();
        fragment.mContext = applicationContext;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.show_all_task_list,container,false);

        mDataShowListView = (ListView)view.findViewById(R.id.data_show);

        DBFunctions dbFunctions = new DBFunctions();
        ArrayList<ProjectModel> projectModel = dbFunctions.getAllTask();
        mShowTaskAdapter = new ShowTaskAdapter(mContext, projectModel );
        mDataShowListView.setAdapter(mShowTaskAdapter);
        return view;
    }
}
