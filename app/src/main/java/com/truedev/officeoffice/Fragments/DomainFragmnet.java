package com.truedev.officeoffice.Fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.truedev.officeoffice.Adapter.DomainAdapter;
import com.truedev.officeoffice.Adapter.ShowTaskAdapter;
import com.truedev.officeoffice.Model.DomainModel;
import com.truedev.officeoffice.R;
import com.truedev.officeoffice.Retrofit.RetrofitRequest;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by dipanshugarg on 2/6/16.
 */
public class DomainFragmnet extends Fragment {

    private ListView mDataShowListView;
    private ShowTaskAdapter mShowTaskAdapter;
    private Context mContext;
    private  ArrayList<DomainModelGet> domainModelGet = new ArrayList<DomainModelGet>();
    private DomainModelGet domainModel;

    public static DomainFragmnet newInstance(Context applicationContext) {
        DomainFragmnet fragment = new DomainFragmnet();
        fragment.mContext = applicationContext;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.show_all_task_list,container,false);
        mDataShowListView = (ListView)view.findViewById(R.id.data_show);

        DomainModel model = new DomainModel();
        Call<DomainModel[]> modelCall = RetrofitRequest.getDomain();

        modelCall.enqueue(new Callback<DomainModel[]>() {
            @Override
            public void onResponse(Response<DomainModel[]> response, Retrofit retrofit) {
                DomainModel[] domainModels = response.body();
                for (int i = 0; i < response.body().length; i++) {
                    domainModel = new DomainModelGet();
                    domainModel.setmCreatedAt( domainModels[i].getmCreatedAt());
                    domainModel.setmId(domainModels[i].getmId());
                    domainModel.setmDomain(domainModels[i].getmDomain());
                    domainModel.setmCreatedBy(domainModels[i].getmCreatedBy());
                    domainModelGet.add(domainModel);

                }
                DomainAdapter domainAdapter = new DomainAdapter(mContext,domainModelGet);
                mDataShowListView.setAdapter(domainAdapter);
                Log.d("ArrayList",domainModelGet+"");

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("failure",t+"");
            }
        });


       /* DBFunctions dbFunctions = new DBFunctions();
        ArrayList<ProjectModel> projectModel = dbFunctions.getAllTask();
        mShowTaskAdapter = new ShowTaskAdapter(mContext, projectModel );
        mDataShowListView.setAdapter(mShowTaskAdapter);*/
        return view;
    }
}
