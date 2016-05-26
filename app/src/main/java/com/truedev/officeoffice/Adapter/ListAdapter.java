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

        View convertView = mInflater.inflate(R.layout.row, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, final int position) {


        holder.iv_delete.setTag(position);
        holder.tv_name.setText(mDataList.get(position).name);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                UserData _state = (UserData) cb.getTag();
                Toast.makeText(mContext, "Clicked on Checkbox: " + cb.getText() + " is " + cb.isChecked(),
                        Toast.LENGTH_LONG).show();

                holder.checkBox.setSelected(cb.isChecked());
            }
        });

        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.nameToChnge(mDataList.get((Integer) v.getTag()).name);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        CheckBox checkBox;
        ImageView iv_delete;

        public ListViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            iv_delete = (ImageView) itemView.findViewById(R.id.iv_delete);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);


        }
    }


}