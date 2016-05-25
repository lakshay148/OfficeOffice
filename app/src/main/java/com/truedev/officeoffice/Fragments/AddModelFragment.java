package com.truedev.officeoffice.Fragments;


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
public class AddModelFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    DailyTaskDB dailyTaskDB;
    private String[] owner = {"Admin", "User"};
    private String[] domain = {"Android", "IOS", "Web"};
    Spinner spinerOwner, spinerDomain;
    EditText empName, empID;
    Button bSubmit;
    Context mContext;

    public static AddModelFragment newInstance(Context applicationContext) {
        AddModelFragment fragment = new AddModelFragment();
        fragment.mContext = applicationContext;
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_module, container, false);
        spinerOwner = (Spinner) view.findViewById(R.id.spinner_owner);
        spinerDomain = (Spinner) view.findViewById(R.id.spinner_domain);
        empID = (EditText) view.findViewById(R.id.et_empID);

        bSubmit = (Button) view.findViewById(R.id.buttonsubmit);
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ID = empID.getText().toString();
                String sdomain = spinerDomain.getSelectedItem().toString();
                String sOwner = spinerOwner.getSelectedItem().toString();

                if (ID.equals("")) {
                    Toast.makeText(getActivity(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    DBFunctions dailyTaskDB = new DBFunctions();
                    dailyTaskDB.insertEntryADDModel(ID, sdomain, sOwner);
                    Toast.makeText(getActivity(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    moveToNewActivity();
                }

            }
        });

        ArrayAdapter<String> adapter_stateOwner = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, owner);
        adapter_stateOwner
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerOwner.setAdapter(adapter_stateOwner);
        spinerOwner.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter_stateDomain = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, domain);
        adapter_stateDomain
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerDomain.setAdapter(adapter_stateDomain);
        spinerDomain.setOnItemSelectedListener(this);
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
        (getActivity()).overridePendingTransition(0, 0);

    }
}
