package com.truedev.officeoffice.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
 *
 * @author Dipanshu Garg
 *         Fragment instantiation with newInstance Done
 *         private member fields Done
 *         member fields should start with m Done
 *         strings to be referred from strings.xml Done
 */


public class AddTaskFragment extends Fragment {
    private EditText mGetTask, mDate;
    private ImageView mAddTask;
    private ListView mListShowData;
    private Button mSave;
    private static String mProject;
    private TextView mdataTask;
    private ArrayList<RowData> mTaskRowData = new ArrayList<RowData>();
    private ArrayList<String> mTask = new ArrayList<String>();
    private AddTaskAdapter mDynamicAdapter;
    private Context mContext;
    private RelativeLayout mRelativeLayoutAddTask;

    public static AddTaskFragment newInstance(Context applicationContext, String project) {
        AddTaskFragment fragment = new AddTaskFragment();
        fragment.mContext = applicationContext;
        mProject = project;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_layout, container, false);
        mGetTask = (EditText) view.findViewById(R.id.get_task);
        mAddTask = (ImageView) view.findViewById(R.id.add_task);
        mListShowData = (ListView) view.findViewById(R.id.add_layout);
        mDate = (EditText) view.findViewById(R.id.date);
        mRelativeLayoutAddTask = (RelativeLayout) view.findViewById(R.id.relativelayout_addtask);
        mRelativeLayoutAddTask.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                hideKeyboard(view);
                return false;
            }
        });
        mSave = (Button) view.findViewById(R.id.save);
        mDate.setText(CommonUtils.getCurrentDate());

        mAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGetTask.getText().toString().equals("")) {
                    Toast.makeText(mContext, "Empty Value", Toast.LENGTH_LONG).show();
                } else {
                    String task = mGetTask.getText().toString();
                    mTaskRowData.add(new RowData(task));
                    mDynamicAdapter = new AddTaskAdapter(getActivity(), mTaskRowData);
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
                Log.d("List Count", count+"");
                for (int i = 0; i < count; i++) {
                    View view2 = mListShowData.getChildAt(i);
                    mdataTask = (TextView) view2.findViewById(R.id.data);
                    mTask.add(mdataTask.getText().toString());
                }
                if(count > 0) {
                    DBFunctions dailyTaskDB = new DBFunctions();
                    long getSuccess = dailyTaskDB.insertTask(mTask, CommonUtils.getCurrentDate(), mProject);
                    if (getSuccess >= 1) {
                        Toast.makeText(getActivity(), "Data Added Successfully", Toast.LENGTH_LONG).show();
                        moveToNewActivity();
                    }
                } else {
                    Toast.makeText(getActivity(), "There is no task", Toast.LENGTH_LONG).show();
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

    protected void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
