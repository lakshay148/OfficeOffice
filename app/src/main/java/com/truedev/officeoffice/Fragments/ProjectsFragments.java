package com.truedev.officeoffice.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.truedev.officeoffice.Adapter.ProjectsAdapter;
import com.truedev.officeoffice.R;

import java.util.ArrayList;

/**
 * Created by dipanshugarg on 18/5/16.
 */

public class ProjectsFragments extends Fragment {

    Context context;
    ListView listView;
    ArrayList<String> project;


    public ProjectsFragments(Context applicationContext) {
        this.context= applicationContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main,container,false);
        listView = (ListView)view.findViewById(R.id.listview);
        project = new ArrayList<>();

        project.add("SFA");
        project.add("Auction");
        project.add("Evaluator");
        project.add("Gcloud");
        project.add("Gcloud");
        project.add("Gcloud IOS");

        ProjectsAdapter projectsAdapter = new ProjectsAdapter(context , project);

        listView.setAdapter(projectsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String  itemValue = (String) listView.getItemAtPosition(position);
                if(itemPosition <= project.size()) {
                    Fragment fragment = new AddTaskFragment(context);
                    Bundle bundle=new Bundle();
                    bundle.putString("Project", ""+((TextView)view.findViewById(R.id.project)).getText().toString());
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).addToBackStack(null).commit();
                }
                Toast.makeText(context, itemValue , Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }
}
