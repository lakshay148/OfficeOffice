package com.truedev.officeoffice.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.truedev.officeoffice.Activity.MainActivity;
import com.truedev.officeoffice.Adapter.AddTaskAdapter;
import com.truedev.officeoffice.CommonUtils;
import com.truedev.officeoffice.DBFunctions;
import com.truedev.officeoffice.Model.RowData;
import com.truedev.officeoffice.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * @author Dipanshu Garg
 * Fragment instantiation with newInstance Done
 * private member fields Done
 * member fields should start with m Done
 * strings to be referred from strings.xml Done
 */


public class AddTaskFragment extends Fragment {

    private EditText mGetTask,mDate;
    private ImageView mAddTask;
    private ListView mListShowData;
    private Button mSave;
    private String mProject;
    private TextView mdataTask;
    private ArrayList<RowData> mTaskRowData = new ArrayList<RowData>();
    private ArrayList<String> mTask = new ArrayList<String>();
    private AddTaskAdapter mDynamicAdapter;
    private Context mContext;

    public static AddTaskFragment newInstance(Context applicationContext) {
        AddTaskFragment fragment = new AddTaskFragment();
        fragment.mContext = applicationContext;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_layout,container,false);

        mProject=getArguments().getString("Project");
        mGetTask = (EditText)view.findViewById(R.id.get_task);
        mAddTask = (ImageView)view.findViewById(R.id.add_task);
        mListShowData = (ListView)view.findViewById(R.id.add_layout);
        mDate = (EditText) view.findViewById(R.id.date);
        mSave = (Button) view.findViewById(R.id.save);
        mDate.setText(CommonUtils.getCurrentDate());

        mAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mGetTask.getText().toString().equals("")) {
                    Toast.makeText(mContext,"Empty Value", Toast.LENGTH_LONG).show();
                }
                else {
                    String task = mGetTask.getText().toString();
                    mTaskRowData.add(new RowData(task));
                    mDynamicAdapter = new AddTaskAdapter(mContext, mTaskRowData);
                    mListShowData.setAdapter(mDynamicAdapter);
                    mGetTask.setText("");
                }
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask.clear();
                int count = mListShowData.getCount();
                for (int i = 0; i<count ; i++ ) {
                    View view2 = mListShowData.getChildAt(i);
                    mdataTask= (TextView) view2.findViewById(R.id.data);
                    mTask.add(mdataTask.getText().toString());
                }
                DBFunctions dailyTaskDB = new DBFunctions();

                long getSuccess= dailyTaskDB.insertTask(mTask,CommonUtils.getCurrentDate(),mProject);
                if(getSuccess>=1) {
                    Toast.makeText(getActivity(),"Data Added Successfully",Toast.LENGTH_LONG).show();
                    moveToNewActivity();
                }
            }
        });


        return view;
    }
    private void moveToNewActivity() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);
    }
}
