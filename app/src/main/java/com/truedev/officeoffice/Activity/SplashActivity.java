package com.truedev.officeoffice.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.truedev.officeoffice.Constants;
import com.truedev.officeoffice.R;

/**
 * Created by dipanshugarg on 19/5/16.
 */
public class SplashActivity extends AppCompatActivity {
    private EditText mId, mPassword;
    private String mUserId, mUserPassword;
    private Button mLogin;
    public static SharedPreferences prefs;
    public static SharedPreferences.Editor toEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        mId = (EditText) findViewById(R.id.empId);
        mPassword = (EditText) findViewById(R.id.editPassword);
        mLogin = (Button) findViewById(R.id.login);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isLoggedIn = prefs.getBoolean(Constants.IS_LOGGED_IN, false);

        if (isLoggedIn) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            mLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mUserId = mId.getText().toString().trim();
                    mUserPassword = mPassword.getText().toString().trim();
                    if (mUserId.equals("") && mUserPassword.equals("")) {

                        mId.setError("Either user id or password is wrong");
                        mPassword.setError("Either user id or password is wrong");

                    } else if (mUserId.equals("")) {
                        mId.setError("Please enter your Id");

                    } else if (mUserPassword.equals("")) {
                        mPassword.setError("Please enter your password");

                    } else if(mUserId.equals("123") && mUserPassword.equals("123")) {
                        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("mykey", Integer.parseInt(mUserId));
                        editor.commit();
                        prefs = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
                        toEdit = prefs.edit();
                        toEdit.putBoolean(Constants.IS_LOGGED_IN, true);
                        toEdit.commit();
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            });
        }
    }
}
