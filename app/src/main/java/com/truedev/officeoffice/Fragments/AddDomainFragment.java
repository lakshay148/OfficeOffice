package com.truedev.officeoffice.Fragments;


import android.app.Activity;
import android.support.v4.app.Fragment;
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
import com.truedev.officeoffice.DBFunctions;
import com.truedev.officeoffice.R;

/** Created by Ankita Sharma
 *
 /*
 */
public class AddDomainFragment extends Fragment {
   private Context mContext;
   private EditText mEditTextdomainname;
   private Button mButton;

    public static AddDomainFragment newInstance(Context applicationContext) {
        AddDomainFragment fragment= new  AddDomainFragment();
        fragment.mContext=applicationContext;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_domain, container, false);
        mEditTextdomainname = (EditText) view.findViewById(R.id.adddomain);
        mButton = (Button) view.findViewById(R.id.add);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namedomain = mEditTextdomainname.getText().toString();
                if (namedomain.equals("")) {
                    Toast.makeText(getActivity(), "Empty entry", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    DBFunctions dailyTaskDB = new DBFunctions();
                    dailyTaskDB.insertEntryAddDomain(namedomain);
                    Toast.makeText(getActivity(), "Doamin Successfully Added", Toast.LENGTH_LONG).show();
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
