package com.truedev.officeoffice.Fragments;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.truedev.officeoffice.Activity.MainActivity;
import com.truedev.officeoffice.DBFunctions;
import com.truedev.officeoffice.Database.DailyTaskDB;
import com.truedev.officeoffice.Model.UserData;
import com.truedev.officeoffice.R;

/**
 * Created by Ankita sharma
 * <p/>
 * /
 */
public class AddPrivilegeFragment extends Fragment {
    private EditText mEt_name;
    private Button mBtn_next;
    private Context mContext;
    private LinearLayout mLinearLayoutAddPrivilege;



    public static AddPrivilegeFragment newInstance(Context applicationContext) {
        AddPrivilegeFragment fragment = new AddPrivilegeFragment();
        fragment.mContext = applicationContext;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_privilege, container, false);


        mEt_name = (EditText) view.findViewById(R.id.et_name);
        mBtn_next = (Button) view.findViewById(R.id.btn_next);
        mLinearLayoutAddPrivilege = (LinearLayout) view.findViewById(R.id.linearlayout_addprivilege);
        mLinearLayoutAddPrivilege.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                hideKeyboard(view);
                return false;
            }
        });


        mBtn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserData userData = new UserData();
                userData.name = mEt_name.getText().toString();
                if (userData.name.equals("")) {
                    Toast.makeText(getActivity(), "Field Vaccant", Toast.LENGTH_LONG).show();
                } else {
                    DBFunctions.insertUserDetail(userData);
                    Toast.makeText(getActivity(), "Privilege added", Toast.LENGTH_SHORT).show();
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
    protected void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
