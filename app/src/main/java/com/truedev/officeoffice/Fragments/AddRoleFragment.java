package com.truedev.officeoffice.Fragments;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
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


public class AddRoleFragment extends Fragment implements Listener, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;
    private EditText mRoleName;
    private CheckBox mCheckBox;
    private ContentValues mContentValues;
    private ArrayList<UserData> mArrayList;
    private DailyTaskDB mDailyTaskDB;
    private Context mContext;
    private Button mSubmit;
    private SQLiteDatabase mSqLiteDatabase;
    private CheckBox checkBox_header;
    private CheckBox mAllCheckBox;
    private LinearLayout mLinearLayoutAddRole;
    SparseBooleanArray mChecked = new SparseBooleanArray();

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
        // mAllCheckBox = (CheckBox) view.findViewById(R.id.chkAll);
        mSubmit = (Button) view.findViewById(R.id.bSubmit);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_contactlist);
        mRoleName = (EditText) view.findViewById(R.id.et_name);
        mLinearLayoutAddRole = (LinearLayout) view.findViewById(R.id.linearlayout_addrole);
        mLinearLayoutAddRole.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                hideKeyboard(view);
                return false;
            }
        });
        mSubmit.setOnClickListener(this);
        mContentValues = new ContentValues();
        mArrayList = new ArrayList<UserData>();
        mAdapter = new ListAdapter(this.getActivity(), DBFunctions.getAllUser());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      /*  mAllCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
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
                .getRoleist();

        for (int i = 0; i < stList.size(); i++) {
            UserData singleStudent = stList.get(i);
            if (singleStudent.isSelected() == true) {
                data = data + "\n" + singleStudent.getName().toString();
            }
        }
        if (roleName.equals("")) {
            Toast.makeText(getActivity(), "Empty entry", Toast.LENGTH_LONG).show();
            return;
        } else {
            mDailyTaskDB = new DailyTaskDB(mContext);
            mSqLiteDatabase = mDailyTaskDB.getWritableDatabase();
            DBFunctions.addRole(roleName, data, mSqLiteDatabase);
            Toast.makeText(getActivity(), "Data saved", Toast.LENGTH_LONG).show();
            mDailyTaskDB.close();
            moveToNewActivity();
        }
    }

       /* public void onItemClick (AdapterView < ? > arg0, View arg1,int arg2, long arg3){

            int checkedItemCount = getCheckedItemCount();

            if (getRoleist.getCount() == checkedItemCount)
                chk.setChecked(true);
            else
                chk.setChecked(false);
        }

        private int getCheckedItemCount(){
            int cnt = 0;
            SparseBooleanArray positions = getRoleist().getCheckedItemPositions();
            int itemCount = getRoleist().getCount();

            for(int i=0;i<itemCount;i++){
                if(positions.get(i))
                    cnt++;
            }
            return cnt;
        }*/


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







