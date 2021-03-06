package com.truedev.officeoffice.Fragments;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.truedev.officeoffice.Activity.MainActivity;
import com.truedev.officeoffice.DBFunctions;
import com.truedev.officeoffice.Model.DomainModel;
import com.truedev.officeoffice.R;
import com.truedev.officeoffice.Retrofit.RetrofitRequest;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Ankita Sharma
 * <p>
 * /*
 */
public class AddDomainFragment extends Fragment {
    private Context mContext;
    private EditText mEditTextdomainname;
    private Button mButton;
    private DomainModel mDomainModel;
    private RelativeLayout mRelativeLayoutDomain;


    public static AddDomainFragment newInstance(Context applicationContext) {
        AddDomainFragment fragment = new AddDomainFragment();
        fragment.mContext = applicationContext;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_domain, container, false);
        mEditTextdomainname = (EditText) view.findViewById(R.id.adddomain);
        mRelativeLayoutDomain = (RelativeLayout) view.findViewById(R.id.releativelayout_domain);
        mButton = (Button) view.findViewById(R.id.add);
        mRelativeLayoutDomain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                hideKeyboard(view);
                return false;
            }
        });
        mDomainModel = new DomainModel();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namedomain = mEditTextdomainname.getText().toString();
                if (namedomain.equals("")) {
                    Toast.makeText(getActivity(), "Empty entry", Toast.LENGTH_LONG).show();
                    return;
                } else {
                  /*  DBFunctions dailyTaskDB = new DBFunctions();
                    dailyTaskDB.insertEntryAddDomain(namedomain);*/
                    mDomainModel.setmDomain(namedomain);
                    mDomainModel.setmCreatedBy("1");
                    Call<DomainModel> call = RetrofitRequest.addDomain(mDomainModel);

                    call.enqueue(new Callback<DomainModel>() {
                        @Override
                        public void onResponse(Response<DomainModel> response, Retrofit retrofit) {
                            Toast.makeText(getActivity(), "Doamin Successfully Added", Toast.LENGTH_LONG).show();
                            moveToNewActivity();
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Log.e("Add Domain Failure", t + "");
                        }
                    });

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
