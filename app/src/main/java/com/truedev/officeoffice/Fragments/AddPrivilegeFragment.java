package com.truedev.officeoffice.Fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.truedev.officeoffice.Activity.MainActivity;
import com.truedev.officeoffice.Database.DailyTaskDB;
import com.truedev.officeoffice.Model.UserData;
import com.truedev.officeoffice.R;

/**
 * A simple {@link Fragment} subclass.
 * @author ?
 *
 * Fragment instantiation with newInstance
 * private member fields
 * member fields should start with m
 * strings to be referred from strings.xml
 */
public class AddPrivilegeFragment extends Fragment {
    EditText et_name;
    Button btn_next;
    DailyTaskDB dbHelper;
    Context context;


    public AddPrivilegeFragment(Context applicationContext) {
        this.context = applicationContext;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_privilege, container, false);
        dbHelper = DailyTaskDB.getInstance(getActivity());

        et_name = (EditText) view.findViewById(R.id.et_name);
        btn_next = (Button) view.findViewById(R.id.btn_next);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserData userData = new UserData();

                if (!et_name.getText().toString().isEmpty()) {
                    userData.name = et_name.getText().toString();
                } else {
                    userData.name = "";
                }

                dbHelper.insertUserDetail(userData);
                Toast.makeText(getActivity(), "Privilege added", Toast.LENGTH_SHORT).show();
                moveToNewActivity();
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
