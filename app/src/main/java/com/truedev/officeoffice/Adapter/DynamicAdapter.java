package com.truedev.officeoffice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.truedev.officeoffice.Model.RowData;
import com.truedev.officeoffice.R;

import java.util.ArrayList;

/**
 * Created by dipanshugarg on 17/5/16.
 */
public class DynamicAdapter extends BaseAdapter {

    Context context;
    ArrayList<RowData> rowDatas;
    public DynamicAdapter(Context context , ArrayList<RowData> list) {
        this.context = context;
        this.rowDatas = list;
    }

    @Override
    public int getCount() {
        return rowDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return rowDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowDatas.indexOf(getItem(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.dynamic_list, parent, false);
        TextView  taskData = (TextView)rowView.findViewById(R.id.data);
        final ImageView button = (ImageView)rowView.findViewById(R.id.remove);
        RowData rowData = (RowData) getItem(position);
        taskData.setText(rowData.getData());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowDatas.remove(position);
                notifyDataSetChanged();
            }
        });
        return rowView;
    }
}
