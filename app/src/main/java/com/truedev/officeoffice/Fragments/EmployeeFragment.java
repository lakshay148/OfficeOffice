package com.truedev.officeoffice.Fragments;


import android.app.Activity;
import android.app.Fragment;
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
import com.truedev.officeoffice.R;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author : ?
 * Fragment instantiation with newInstance
 * private member fields
 * member fields should start with m
 * strings to be referred from strings.xml
 *
 */
public class EmployeeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private String[] mRole = {"R1", "R2", "R3", "R4"};
    private Spinner mSpinerRole;
    private EditText mEmp_name, mEmp_id, mEmp_password;
    private Button mbutton;

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

        mEmp_id = (EditText) view.findViewById(R.id.et_empID);
        mEmp_name = (EditText) view.findViewById(R.id.et_empName);
        mEmp_password = (EditText) view.findViewById(R.id.et_empPassword);
        mSpinerRole = (Spinner) view.findViewById(R.id.spinner);
        mbutton = (Button) view.findViewById(R.id.submit);
        mbutton.setOnClickListener(this);

        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, mRole);
        adapter_state
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinerRole.setAdapter(adapter_state);
        mSpinerRole.setOnItemSelectedListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String empName = mEmp_name.getText().toString();
        String password = mEmp_password.getText().toString();
        String empID = mEmp_id.getText().toString();
        String item = mSpinerRole.getSelectedItem().toString();

        if (empName.equals("") || password.equals("") || empID.equals("")) {
            Toast.makeText(getActivity(), "Field Vaccant", Toast.LENGTH_LONG).show();
            return;
        } else {
            DBFunctions dailyTaskDB = new DBFunctions();
            dailyTaskDB.insertEntry(empName, password, empID, item);
            Toast.makeText(getActivity(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
            moveToNewActivity();
        }
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
