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
import com.truedev.officeoffice.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDomainFragment extends Fragment {
    Context context;
    EditText editTextdomainname;
    Button button;

    public AddDomainFragment(Context applicationContext) {
        this.context = applicationContext;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_domain, container, false);
        editTextdomainname = (EditText) view.findViewById(R.id.adddomain);
        button = (Button) view.findViewById(R.id.add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namedomain = editTextdomainname.getText().toString();
                if (namedomain.equals("")) {
                    Toast.makeText(getActivity(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    DailyTaskDB dailyTaskDB = new DailyTaskDB(context);
                    dailyTaskDB.insertEntryAddDomain(namedomain);
                    Toast.makeText(getActivity(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    moveToNewActivity();
                }
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
