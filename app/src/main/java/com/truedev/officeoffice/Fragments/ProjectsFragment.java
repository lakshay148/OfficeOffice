package com.truedev.officeoffice.Fragments;

import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.truedev.officeoffice.Adapter.ProjectsAdapter;
import com.truedev.officeoffice.R;

import java.util.ArrayList;

/**
 * Created by dipanshugarg on 18/5/16.
 * <p/>
 * <p/>
 * <p/>
 * Review Comments :
 * <p/>
 * Fragment instantiation with newInstance
 * private member fields
 * member fields should start with m
 * strings to be referred from strings.xml
 * add projects from db
 * Add List through generalized Base Adapter
 * put extras in constants
 */

public class ProjectsFragment extends Fragment {

    private ListView listView;
    private ArrayList<String> project;

    private Context mContext;
    private LinearLayout mLinearlayout_project;

    public static ProjectsFragment newInstance(Context applicationContext) {
        ProjectsFragment fragment = new ProjectsFragment();
        fragment.mContext = applicationContext;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        mLinearlayout_project = (LinearLayout) view.findViewById(R.id.linearlayout_project);
        mLinearlayout_project.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                hideKeyboard(view);
                return false;
            }
        });
        project = new ArrayList<>();

        project.add("SFA");
        project.add("Auction");
        project.add("Evaluator");
        project.add("Gcloud");
        project.add("Gcloud IOS");

        ProjectsAdapter projectsAdapter = new ProjectsAdapter(getActivity(), project);

        listView.setAdapter(projectsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) listView.getItemAtPosition(position);
                if (itemPosition <= project.size()) {
                    String project = ((TextView) view.findViewById(R.id.project)).getText().toString();
                    getFragmentManager().beginTransaction().replace(R.id.content_frame, AddTaskFragment.newInstance(mContext, project)).addToBackStack(null).commit();
                }
                Toast.makeText(getActivity(), itemValue, Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

    protected void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
