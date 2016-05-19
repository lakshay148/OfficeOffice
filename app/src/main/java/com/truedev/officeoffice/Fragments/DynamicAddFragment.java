package com.truedev.officeoffice.Fragments;

import android.app.Fragment;
import android.content.Context;
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


import com.truedev.officeoffice.Adapter.DynamicAdapter;
import com.truedev.officeoffice.Database.DailyTaskDB;
import com.truedev.officeoffice.Model.RowData;
import com.truedev.officeoffice.R;

import java.util.ArrayList;


public class DynamicAddFragment extends Fragment {

    Context context;
    EditText editText;
    ImageView addTask;
    ListView listView;
    Button save;
    private ArrayList<RowData> items = new ArrayList<RowData>();
    private ArrayList<String> task = new ArrayList<String>();
    DynamicAdapter dynamicAdapter;
    public DynamicAddFragment(Context context) {
        this.context  = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_layout,container,false);
        editText = (EditText)view.findViewById(R.id.get_task);
        addTask = (ImageView)view.findViewById(R.id.add_task);
        listView = (ListView)view.findViewById(R.id.add_layout);
        save = (Button) view.findViewById(R.id.save);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("")) {
                    Toast.makeText(context,"Empty Value", Toast.LENGTH_LONG).show();
                }
                else {
                    String task = editText.getText().toString();
                    items.add(new RowData(task));
                    dynamicAdapter = new DynamicAdapter(context, items);
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
                    View view = listView.getChildAt(i);
                    TextView textView = (TextView) view.findViewById(R.id.data);
                    task.add(textView.getText()+"");
                }
                DailyTaskDB dailyTaskDB = new DailyTaskDB(context);
                long getSuccess= dailyTaskDB.insertTask(task);
                if(getSuccess>=1) {
                    Toast.makeText(getActivity(),"Data Added Successfully",Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }
}
