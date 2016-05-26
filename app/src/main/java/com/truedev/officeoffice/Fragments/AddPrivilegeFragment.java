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
import com.truedev.officeoffice.Database.DailyTaskDB;
import com.truedev.officeoffice.Model.UserData;
import com.truedev.officeoffice.R;

/**
 * A simple {@link Fragment} subclass.
 * @author ?    Ankita                            /done
 *
 * Fragment instantiation with newInstance  /done
 * private member fields                    /done
 * member fields should start with m        /done
 * strings to be referred from strings.xml  /done
 */
public class AddPrivilegeFragment extends Fragment {
   private EditText met_name;
   private Button mbtn_next;
   private Context mcontext;


    public static AddPrivilegeFragment newInstance(Context applicationContext) {
        AddPrivilegeFragment fragment = new AddPrivilegeFragment();
        fragment.mcontext=applicationContext;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_privilege, container, false);


        met_name = (EditText) view.findViewById(R.id.et_name);
        mbtn_next = (Button) view.findViewById(R.id.btn_next);


        mbtn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserData userData = new UserData();

                if (!met_name.getText().toString().isEmpty()) {
                    userData.name = met_name.getText().toString();
                } else {
                    userData.name = "";
                }

                DBFunctions.insertUserDetail(userData);
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
