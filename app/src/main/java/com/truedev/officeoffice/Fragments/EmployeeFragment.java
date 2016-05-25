package com.truedev.officeoffice.Fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
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
    private String[] role = {"R1", "R2", "R3", "R4"};
    Spinner spinerRole;
    EditText emp_name, emp_id, emp_password;
    Button button;
    DailyTaskDB dailyTaskDB;
    Context context;

    public EmployeeFragment(Context applicationContext) {
        this.context= applicationContext;    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee, container, false);

        emp_id = (EditText) view.findViewById(R.id.et_empID);
        emp_name = (EditText) view.findViewById(R.id.et_empName);
        emp_password = (EditText) view.findViewById(R.id.et_empPassword);
        spinerRole = (Spinner) view.findViewById(R.id.spinner);
        button = (Button) view.findViewById(R.id.submit);
        button.setOnClickListener(this);

        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, role);
        adapter_state
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerRole.setAdapter(adapter_state);
        spinerRole.setOnItemSelectedListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String empName = emp_name.getText().toString();
        String password = emp_password.getText().toString();
        String empID = emp_id.getText().toString();
        String item = spinerRole.getSelectedItem().toString();

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
