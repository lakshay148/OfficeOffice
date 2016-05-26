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
public class AddTaskAdapter extends BaseAdapter {

    private Context mContext ;
    private ArrayList<RowData> mRowDatas;
    private TextView  mTaskData;
    private ImageView mRemoveData;
    private RowData mRowData;

    public AddTaskAdapter(Context context , ArrayList<RowData> list) {
        this.mContext = context;
        this.mRowDatas = list;
    }

    @Override
    public int getCount() {
        return mRowDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mRowDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mRowDatas.indexOf(getItem(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.dynamic_list, parent, false);
        mTaskData = (TextView)rowView.findViewById(R.id.data);
        mRemoveData = (ImageView)rowView.findViewById(R.id.remove);
        mRowData = (RowData) getItem(position);
        mTaskData.setText(mRowData.getData());

        mRemoveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRowDatas.remove(position);
                notifyDataSetChanged();
            }
        });
        return rowView;
    }
}
