package com.truedev.officeoffice.Fragments;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
 *
 * @author : prashant chauhan
 *         Fragment instantiation with newInstance:done
 *         private member fields :done
 *         member fields should start with m :done
 *         strings to be referred from strings.xml :done
 */
public class EmployeeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private String[] mRole = {"R1", "R2", "R3", "R4"};
    private EditText mEt_name, mEt_empid, mEt_password;
    private Button mBtn_submit;
    private DailyTaskDB dbHelper;
    private Spinner mSpinner;

    Context mContext;

    public static EmployeeFragment newInstance(Context applicationContext) {
        EmployeeFragment fragment = new EmployeeFragment();
        fragment.mContext = applicationContext;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee, container, false);


        mSpinner = (Spinner) view.findViewById(R.id.spinner);
        mEt_name = (EditText) view.findViewById(R.id.et_name);
        mEt_empid = (EditText) view.findViewById(R.id.et_ID);
        mEt_password = (EditText) view.findViewById(R.id.et_password);
        mBtn_submit = (Button) view.findViewById(R.id.btn_next);

        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, mRole);
        adapter_state
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter_state);
        mSpinner.setOnItemSelectedListener(this);

        mBtn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserData userData = new UserData();

                userData.emp_name = mEt_name.getText().toString();
                userData.emp_id = mEt_empid.getText().toString();
                userData.emp_password = mEt_password.getText().toString();
                userData.emp_role = mSpinner.getSelectedItem().toString();

                if (userData.emp_name.equals("") || userData.emp_id.equals("") || userData.emp_password.equals("")) {
                    Toast.makeText(getActivity(), "Field Vaccant", Toast.LENGTH_LONG).show();
                } else {
                    DBFunctions.insertEmpDetail(userData);
                    Toast.makeText(getActivity(), "inserted", Toast.LENGTH_SHORT).show();
                    moveToNewActivity();
                }

            }
        });

        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void moveToNewActivity() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);

    }
}
