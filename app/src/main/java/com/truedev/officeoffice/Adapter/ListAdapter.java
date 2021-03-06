package com.truedev.officeoffice.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.truedev.officeoffice.Interface.Listener;
import com.truedev.officeoffice.Model.UserData;
import com.truedev.officeoffice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ankita sharma
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<UserData> mDataList = new ArrayList<>();
    private Listener mListner;

    public ListAdapter(Context context, List<UserData> dataList1) {

        this.mContext = context;
        this.mDataList = dataList1;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = mInflater.inflate(R.layout.add_role_list, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, final int position) {
        final int pos = position;

       // holder.iv_delete.setTag(position);
        holder.tv_name.setText(mDataList.get(position).name);
        holder.chkSelected.setChecked(mDataList.get(position).isSelected());

        holder.chkSelected.setTag(mDataList.get(position));
        holder.chkSelected.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                UserData contact = (UserData) cb.getTag();
                contact.setSelected(cb.isChecked());
                mDataList.get(pos).setSelected(cb.isChecked());
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public CheckBox chkSelected;
        public ImageView iv_delete;
        public UserData userData;
        public ListViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            iv_delete = (ImageView) itemView.findViewById(R.id.iv_delete);
            chkSelected = (CheckBox) itemView.findViewById(R.id.checkbox);


        }
    }

    public List<UserData> getRoleist() {
        return mDataList;
    }
}
