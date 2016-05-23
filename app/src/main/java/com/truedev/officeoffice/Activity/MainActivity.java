package com.truedev.officeoffice.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.truedev.officeoffice.Constants;
import com.truedev.officeoffice.Fragments.AddDomainFragment;
import com.truedev.officeoffice.Fragments.AddModelFragment;
import com.truedev.officeoffice.Fragments.EmployeeFragment;
import com.truedev.officeoffice.Fragments.ProjectsFragments;
import com.truedev.officeoffice.Fragments.ShowAllTask;
import com.truedev.officeoffice.R;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private Toolbar mToolbar;
    private FrameLayout mFrameLayout;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        initializeView();
        setUpDrawerLayout();

        final String[] values = new String[]{"Projects", "Add Model", "EmployeeFragment", "Add Domain", "Logout"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
                drawerLayout.closeDrawers();
            }


        });

    }

    private void selectItem(int position) {
        switch (position) {
            case 0:
                Fragment fragment = new ProjectsFragments(getApplicationContext());
                getSupportActionBar().setTitle("Projects");
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                break;

            case 1:
                Fragment fragment1 = new AddModelFragment(getApplicationContext());
                getSupportActionBar().setTitle("Add Model");
                FragmentManager fragmentManager1 = getFragmentManager();
                fragmentManager1.beginTransaction().replace(R.id.content_frame, fragment1).commit();
                break;
            case 2:
                Fragment fragment2 = new EmployeeFragment(getApplicationContext());
                getSupportActionBar().setTitle("EmployeeFragment");
                FragmentManager fragmentManager2 = getFragmentManager();
                fragmentManager2.beginTransaction().replace(R.id.content_frame, fragment2).commit();
                break;
            case 3:
                Fragment fragment3 = new AddDomainFragment(getApplicationContext());
                getSupportActionBar().setTitle("AddDomainFragment");
                FragmentManager fragmentManager3 = getFragmentManager();
                fragmentManager3.beginTransaction().replace(R.id.content_frame, fragment3).commit();
                break;


            case 4:
                Fragment fragment1 = new ShowAllTask(getApplicationContext());
                FragmentManager fragmentManager1= getFragmentManager();
                fragmentManager1.beginTransaction().replace(R.id.content_frame,fragment1).commit();
                break;
            case 5:
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
                drawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
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

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0) {

            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

    }

    private void initializeView() {
        listView = (ListView) findViewById(R.id.list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Fragment fragment = new ProjectsFragments(getApplicationContext());
        getSupportActionBar().setTitle("Projects");
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawers();
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount()> 0) {
                getSupportFragmentManager().popBackStack();

            } else {
                super.onBackPressed();
            }
        }
    }
}
