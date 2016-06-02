package com.truedev.officeoffice.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.truedev.officeoffice.Fragments.DomainModelGet;
import com.truedev.officeoffice.Model.DomainModel;
import com.truedev.officeoffice.Model.RowData;
import com.truedev.officeoffice.Model.UserData;
import com.truedev.officeoffice.R;

import java.util.ArrayList;

/**
 * Created by dipanshugarg on 2/6/16.
 */
public class DomainAdapter  extends RecyclerView.Adapter<DomainAdapter.DomainViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext ;
    private ArrayList<DomainModelGet> mRowDatas;
    private TextView mId;
    private TextView mName;
    private TextView mCreatedAt;
    private TextView mCreatedBy;

    public DomainAdapter(Context mContext, ArrayList<DomainModelGet> domainModelGet) {
        this.mContext= mContext;
        this.mRowDatas = domainModelGet;
        mInflater = LayoutInflater.from(mContext);
    }

  /*  @Override
    public int getCount() {
        return mRowDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mRowDatas.get(position);
    }
*/
    @Override
    public DomainAdapter.DomainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.show_domain, parent, false);
        DomainViewHolder viewHolder = new DomainViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final DomainViewHolder holder,final int position) {
        holder.mId.setText("ID:" + String.valueOf(mRowDatas.get(position).getmId()));
        holder.mName.setText("Domain:"+ mRowDatas.get(position).getmDomain());
        holder.mCreatedAt.setText("Created At:"+ mRowDatas.get(position).getmCreatedAt());
        holder.mCreatedBy.setText("Created By:"+ mRowDatas.get(position).getmCreatedBy());

    }


    @Override
    public int getItemCount() {
        return mRowDatas.size();
    }

   /* @Override
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
    }*/
    class DomainViewHolder extends RecyclerView.ViewHolder {
       public TextView mId;
       public TextView mName;
       public TextView mCreatedAt;
       public TextView mCreatedBy;
       public DomainModel domainMode;
        public DomainViewHolder(View itemView) {
            super(itemView);

            mId = (TextView)itemView.findViewById(R.id.id_name);
            mName = (TextView)itemView.findViewById(R.id.name);
            mCreatedAt = (TextView)itemView.findViewById(R.id.created_at);
            mCreatedBy = (TextView)itemView.findViewById(R.id.created_by);

        }
    }
}
