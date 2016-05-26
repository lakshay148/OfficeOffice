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

/** created by Ankita Sharma
 *
 /*
 * @author ?                                   /done
 * Fragment instantiation with newInstance   /done
 * private member fields                     /done
 * member fields should start with m         /done
 * strings to be referred from strings.xml   /done
 *
 * Role not being inserted in db
 */


public class AddRoleFragment extends Fragment implements Listener, View.OnClickListener {

   private RecyclerView mRecyclerView;
   private ListAdapter mAdapter;
    private EditText mRoleName;
    private CheckBox mCheckBox;
    private ContentValues mContentValues = new ContentValues();
    private ArrayList<UserData> mArrayList = new ArrayList<UserData>();
    private DailyTaskDB mDailyTaskDB;
    private Context mContext;
    
    public static AddRoleFragment newInstance(Context applicationContext) {
        AddRoleFragment fragment= new AddRoleFragment();
        fragment.mContext=applicationContext;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_role, container, false);
        mDailyTaskDB = DBFunctions.getInstance(getActivity());
        mCheckBox = (CheckBox) view.findViewById(R.id.checkbox);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_contactlist);
        mRoleName= (EditText) view.findViewById(R.id.et_name);
        mAdapter = new ListAdapter(this.getActivity(), DBFunctions.getAllUser());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    /* @Override
     public void onBackPressed() {
         super.onBackPressed();
         Intent intent = new Intent(getActivity(), MainActivity.class);
         startActivity(intent);
     }*/


    @Override
    public void nameToChnge(String name) {
        DBFunctions.deleteRow(name);
        mAdapter = new ListAdapter(getActivity(), DBFunctions.getAllUser());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onClick(View v) {
        UserData userData = new UserData();

        if (!mRoleName.getText().toString().isEmpty()) {
            userData.roleName = mRoleName.getText().toString();
        } else {
            userData.roleName = "";
        }
        DBFunctions.insertUserRole(userData);
        Toast.makeText(mContext, "inserted", Toast.LENGTH_SHORT).show();
        moveToNewActivity();

    }
    private void moveToNewActivity() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);

    }
}







