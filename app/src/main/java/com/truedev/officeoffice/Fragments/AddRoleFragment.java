package com.truedev.officeoffice.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.truedev.officeoffice.Activity.MainActivity;
import com.truedev.officeoffice.Adapter.ListAdapter;
import com.truedev.officeoffice.DBFunctions;
import com.truedev.officeoffice.Database.DailyTaskDB;
import com.truedev.officeoffice.Interface.Listener;
import com.truedev.officeoffice.Model.UserData;
import com.truedev.officeoffice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ankita Sharma
 * <p/>
 * /*
 */


public class AddRoleFragment extends DialogFragment implements Listener, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;
    private EditText mRoleName;
    private CheckBox mCheckBox;
    private ContentValues mContentValues = new ContentValues();
    private ArrayList<UserData> mArrayList = new ArrayList<UserData>();
    private DailyTaskDB mDailyTaskDB;
    private Context mContext;
    private Button mSubmit;
    private SQLiteDatabase mSqLiteDatabase;

    public static AddRoleFragment newInstance(Context applicationContext) {
        AddRoleFragment fragment = new AddRoleFragment();
        fragment.mContext = applicationContext;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_role, container, false);
        mDailyTaskDB = DBFunctions.getInstance(getActivity());
        mCheckBox = (CheckBox) view.findViewById(R.id.checkbox);
        mSubmit = (Button) view.findViewById(R.id.bSubmit);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_contactlist);
        mRoleName = (EditText) view.findViewById(R.id.et_name);
        mSubmit.setOnClickListener(this);
        mAdapter = new ListAdapter(this.getActivity(), DBFunctions.getAllUser());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void nameToChnge(String name) {
       /* DBFunctions.deleteRow(name);
        mAdapter = new ListAdapter(getActivity(), DBFunctions.getAllUser());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));*/
    }


    @Override
    public void onClick(View v) {
        String roleName = mRoleName.getText().toString();
        String data = "";
        List<UserData> stList = ((ListAdapter) mAdapter)
                .getStudentist();

        for (int i = 0; i < stList.size(); i++) {
            UserData singleStudent = stList.get(i);
            if (singleStudent.isSelected() == true) {
                data = data + "\n" + singleStudent.getName().toString();
            }
        }

        mDailyTaskDB = new DailyTaskDB(mContext);
        mSqLiteDatabase = mDailyTaskDB.getWritableDatabase();
        DBFunctions.addRole(roleName, data, mSqLiteDatabase);
        Toast.makeText(getActivity(), "Data saved", Toast.LENGTH_LONG).show();
        mDailyTaskDB.close();

        moveToNewActivity();

    }

    private void moveToNewActivity() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);

    }
}







