package com.truedev.officeoffice.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.truedev.officeoffice.Activity.MainActivity;
import com.truedev.officeoffice.DBFunctions;
import com.truedev.officeoffice.Database.DailyTaskDB;
import com.truedev.officeoffice.Model.UserData;
import com.truedev.officeoffice.R;

/**
 * A simple {@link Fragment} subclass.
 *@auther : Prashant chauhan
 *
 *
 * Review Comments :
 * default Constructor
 * private fields
 * strings to be referred from strings.xml
 * insert data with class objects
 *
 * owner and domain to be picked from db
 *
 */
public class AddModuleFragment extends Fragment  {
    private String[] mRole = {"Admin", "User"};
    private String[] mDomain = {"Android", "IOS", "Web"};
    private EditText mEmpID;
    private Spinner mSpinnerRole, mSpinnerDomain;
    private Button mBtn_submit;
    private DailyTaskDB dbHelper;
    private Context mContext;

    public static AddModuleFragment newInstance(Context applicationContext) {
        AddModuleFragment fragment = new AddModuleFragment();
        fragment.mContext = applicationContext;
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_module, container, false);
        mSpinnerDomain = (Spinner) view.findViewById(R.id.spinner_domain);
        mSpinnerRole = (Spinner) view.findViewById(R.id.spinner_role);
        mEmpID = (EditText) view.findViewById(R.id.et_ID);
        mBtn_submit = (Button) view.findViewById(R.id.btn_submit);

        ArrayAdapter<String> adapter_state_role = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, mRole);
        adapter_state_role
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerRole.setAdapter(adapter_state_role);

        ArrayAdapter<String> adapter_state_domain = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, mDomain);
        adapter_state_domain
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerDomain.setAdapter(adapter_state_domain);

        mBtn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserData userData = new UserData();

                userData.module_empID = mEmpID.getText().toString();
                userData.module_role = mSpinnerRole.getSelectedItem().toString();
                userData.module_domain = mSpinnerDomain.getSelectedItem().toString();

                if ( userData.emp_id.equals("")) {
                    Toast.makeText(getActivity(), "Field Vaccant", Toast.LENGTH_LONG).show();
                } else {
                    DBFunctions.insertModuleDetail(userData);
                    Toast.makeText(getActivity(), "inserted", Toast.LENGTH_SHORT).show();
                    moveToNewActivity();
                }
            }
        });
        return view;
    }

    private void moveToNewActivity() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        (getActivity()).overridePendingTransition(0, 0);

    }
}
