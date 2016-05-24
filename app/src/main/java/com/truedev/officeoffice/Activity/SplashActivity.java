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

import com.truedev.officeoffice.ApplicationController;
import com.truedev.officeoffice.Constants;
import com.truedev.officeoffice.R;

/**
 * Created by dipanshugarg on 19/5/16.
 */
public class SplashActivity extends AppCompatActivity {
    EditText id, password;
    String userId, userPassword;
    Button login;
    public static SharedPreferences prefs;
    public static SharedPreferences.Editor toEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        id = (EditText) findViewById(R.id.empId);
        password = (EditText) findViewById(R.id.editPassword);
        login = (Button) findViewById(R.id.login);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isLoggedIn = prefs.getBoolean(Constants.IS_LOGGED_IN, false);

        if (isLoggedIn) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userId = id.getText().toString().trim();
                    userPassword = password.getText().toString().trim();
                    if (userId.equals("") && userPassword.equals("")) {

                        id.setError("Either user id or password is wrong");
                        password.setError("Either user id or password is wrong");

                    } else if (userId.equals("")) {
                        id.setError("Please enter your Id");

                    } else if (userPassword.equals("")) {
                        password.setError("Please enter your password");

                    } else if(userId.equals("123") && userPassword.equals("123")) {
                        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("mykey", Integer.parseInt(userId));
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
