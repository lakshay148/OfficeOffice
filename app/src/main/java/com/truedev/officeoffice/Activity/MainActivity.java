package com.truedev.officeoffice.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.truedev.officeoffice.Constants;
import com.truedev.officeoffice.Fragments.AddDomainFragment;
import com.truedev.officeoffice.Fragments.AddModuleFragment;
import com.truedev.officeoffice.Fragments.AddPrivilegeFragment;
import com.truedev.officeoffice.Fragments.AddRoleFragment;
import com.truedev.officeoffice.Fragments.DomainFragment;
import com.truedev.officeoffice.Fragments.EmployeeFragment;
import com.truedev.officeoffice.Fragments.ProjectsFragment;
import com.truedev.officeoffice.Fragments.ShowAllTask;
import com.truedev.officeoffice.R;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Toolbar mToolbar;
    private FrameLayout mFrameLayout;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private  int mCurrentPosition = -1;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        //ApplicationController.insertProject();
        initializeView();
        setUpDrawerLayout();

        final String[] values = new String[]{"Projects", "Add Module", "Employee Fragment", "Add Domain", "Add Privilege","Add Role","Show All Task" , "Show Domain","Logout"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
                mDrawerLayout.closeDrawers();
            }

        });

        selectItem(0);

    }

    private void selectItem(int position) {
        mCurrentPosition = position;
        switch (position) {
            case 0:
                getSupportActionBar().setTitle("Projects");
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, ProjectsFragment.newInstance(this)).commit();
                break;

           case 1:
                getSupportActionBar().setTitle("Add Model");
               getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, AddModuleFragment.newInstance(this)).commit();
                break;
            case 2:
                getSupportActionBar().setTitle("EmployeeFragment");
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, EmployeeFragment.newInstance(this)).commit();
                break;
            case 3:
                getSupportActionBar().setTitle("Add Domain");
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, AddDomainFragment.newInstance(this)).commit();
                break;
            case 4:
                getSupportActionBar().setTitle("Add Privilege");
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, AddPrivilegeFragment.newInstance(this)).commit();
                break;
              case 5:
                getSupportActionBar().setTitle("Add Role");
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, AddRoleFragment.newInstance(this)).commit();
                break;

            case 6:
                getSupportActionBar().setTitle("Show All Task");
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,ShowAllTask.newInstance(this)).commit();
                break;

            case 7:
                getSupportActionBar().setTitle("Show Domain");
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, DomainFragment.newInstance(this)).commit();
                break;

            case 8:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor toEdit = prefs.edit();
                toEdit.putBoolean(Constants.IS_LOGGED_IN, false);
                toEdit.commit();
                finish();
                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(intent);
                break;

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void setUpDrawerLayout() {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0) {

            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

    }

    private void initializeView() {
        mListView = (ListView) findViewById(R.id.list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setTitle("Projects");
        //getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, ProjectsFragment.newInstance(this)).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
        } else if (mCurrentPosition!=0) {
            mCurrentPosition = 0;
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, ProjectsFragment.newInstance(this)).commit();
        } else {
            if (doubleBackToExitPressedOnce){
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please press BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }
}
