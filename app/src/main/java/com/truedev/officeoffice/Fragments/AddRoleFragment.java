package com.truedev.officeoffice.Fragments;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.truedev.officeoffice.Adapter.ListAdapter;
import com.truedev.officeoffice.DBFunctions;
import com.truedev.officeoffice.Database.DailyTaskDB;
import com.truedev.officeoffice.Interface.Listener;
import com.truedev.officeoffice.Model.UserData;
import com.truedev.officeoffice.R;

import java.util.ArrayList;

/**
 * @author ?
 * Fragment instantiation with newInstance
 * private member fields
 * member fields should start with m
 * strings to be referred from strings.xml
 *
 * Role not being inserted in db
 */


public class AddRoleFragment extends Fragment implements Listener, View.OnClickListener {
    Context context;
    RecyclerView recyclerView;
    ListAdapter adapter;
    CheckBox checkBox;
    private ContentValues contentValues = new ContentValues();
    ArrayList<UserData> arrayList = new ArrayList<UserData>();
    DailyTaskDB dailyTaskDB;


    public AddRoleFragment(Context applicationContext) {
        this.context = applicationContext;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_role, container, false);
        dailyTaskDB = DBFunctions.getInstance(getActivity());
        checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_contactlist);
        adapter = new ListAdapter(this.getActivity(), DBFunctions.getAllUser());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        adapter = new ListAdapter(getActivity(), DBFunctions.getAllUser());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onClick(View v) {

    }
}







