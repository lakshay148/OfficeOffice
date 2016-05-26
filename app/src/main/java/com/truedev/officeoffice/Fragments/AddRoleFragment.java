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

/**
 * @author ?Ankita
 * Fragment instantiation with newInstance   /done
 * private member fields                     /done
 * member fields should start with m         /done
 * strings to be referred from strings.xml   /done
 *
 * Role not being inserted in db
 */


public class AddRoleFragment extends Fragment implements Listener, View.OnClickListener {

   private RecyclerView mrecyclerView;
   private ListAdapter madapter;
    private EditText mroleName;
    private CheckBox mcheckBox;
    private ContentValues contentValues = new ContentValues();
    private ArrayList<UserData> arrayList = new ArrayList<UserData>();
    private DailyTaskDB mdailyTaskDB;
    private Context mcontext;
    
    public static AddRoleFragment newInstance(Context applicationContext) {
        AddRoleFragment fragment= new AddRoleFragment();
        fragment.mcontext=applicationContext;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_role, container, false);
        mdailyTaskDB = DBFunctions.getInstance(getActivity());
        mcheckBox = (CheckBox) view.findViewById(R.id.checkbox);
        mrecyclerView = (RecyclerView) view.findViewById(R.id.rv_contactlist);
        mroleName= (EditText) view.findViewById(R.id.et_name);
        madapter = new ListAdapter(this.getActivity(), DBFunctions.getAllUser());
        mrecyclerView.setAdapter(madapter);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        madapter = new ListAdapter(getActivity(), DBFunctions.getAllUser());
        mrecyclerView.setAdapter(madapter);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onClick(View v) {
        UserData userData = new UserData();

        if (!mroleName.getText().toString().isEmpty()) {
            userData.name = mroleName.getText().toString();
        } else {
            userData.name = "";
        }
        DBFunctions.insertUserRole(userData);
        Toast.makeText(mcontext, "inserted", Toast.LENGTH_SHORT).show();

        moveToNewActivity();

    }
    private void moveToNewActivity() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);

    }
}







