package com.truedev.officeoffice.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.truedev.officeoffice.Activity.MainActivity;
import com.truedev.officeoffice.Adapter.AddTaskAdapter;
import com.truedev.officeoffice.CommonUtils;
import com.truedev.officeoffice.DBFunctions;
import com.truedev.officeoffice.Model.RowData;
import com.truedev.officeoffice.R;

import java.util.ArrayList;


public class AddTaskFragment extends Fragment {

    Context context;
    EditText editText,date;
    ImageView addTask;
    ListView listView;
    Button save;
    String project;
    TextView dataTask;
    private ArrayList<RowData> items = new ArrayList<RowData>();
    private ArrayList<String> task = new ArrayList<String>();
    AddTaskAdapter dynamicAdapter;
    public AddTaskFragment(Context context) {
        this.context  = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_layout,container,false);

        project=getArguments().getString("Project");

        editText = (EditText)view.findViewById(R.id.get_task);
        addTask = (ImageView)view.findViewById(R.id.add_task);
        listView = (ListView)view.findViewById(R.id.add_layout);
        date = (EditText) view.findViewById(R.id.date);
        save = (Button) view.findViewById(R.id.save);
        date.setText(CommonUtils.getCurrentDate());

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("")) {
                    Toast.makeText(context,"Empty Value", Toast.LENGTH_LONG).show();
                }
                else {
                    String task = editText.getText().toString();
                    items.add(new RowData(task));
                    dynamicAdapter = new AddTaskAdapter(context, items);
                    listView.setAdapter(dynamicAdapter);
                    editText.setText("");
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.clear();
                int count = listView.getCount();
                for (int i = 0; i<count ; i++ ) {
                    View view2 = listView.getChildAt(i);
                    dataTask= (TextView) view2.findViewById(R.id.data);
                    task.add(dataTask.getText().toString());
                }
                DBFunctions dailyTaskDB = new DBFunctions();

                long getSuccess= dailyTaskDB.insertTask(task,CommonUtils.getCurrentDate(),project);
                if(getSuccess>=1) {
                    Toast.makeText(getActivity(),"Data Added Successfully",Toast.LENGTH_LONG).show();
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
