package com.truedev.officeoffice.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.truedev.officeoffice.Fragments.DomainModelGet;
import com.truedev.officeoffice.Model.DomainModel;
import com.truedev.officeoffice.Model.RowData;
import com.truedev.officeoffice.R;

import java.util.ArrayList;

/**
 * Created by dipanshugarg on 2/6/16.
 */
public class DomainAdapter  extends BaseAdapter {

    private Context mContext ;
    private ArrayList<DomainModelGet> mRowDatas;
    private TextView mId;
    private TextView mName;
    private TextView mCreatedAt;
    private TextView mCreatedBy;

    public DomainAdapter(Context mContext, ArrayList<DomainModelGet> domainModelGet) {
        this.mContext= mContext;
        this.mRowDatas = domainModelGet;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.show_domain,parent,false);

        mId = (TextView)view.findViewById(R.id.id_name);
        mName = (TextView)view.findViewById(R.id.name);
        mCreatedAt = (TextView)view.findViewById(R.id.created_at);
        mCreatedBy = (TextView)view.findViewById(R.id.created_by);

        mId.setText("ID:" + String.valueOf(mRowDatas.get(position).getmId()));
        mName.setText("Domain:"+ mRowDatas.get(position).getmDomain());
        mCreatedAt.setText("Created At:"+ mRowDatas.get(position).getmCreatedAt());
        mCreatedBy.setText("Created By:"+ mRowDatas.get(position).getmCreatedBy());
        return view;
    }
}
