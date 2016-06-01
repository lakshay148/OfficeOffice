package com.truedev.officeoffice.Retrofit;

import com.truedev.officeoffice.Model.DomainModel;

import retrofit.Call;

/**
 * Created by dipanshugarg on 2/6/16.
 */
public class RetrofitRequest {
    public static RetrofitInterface retrofitInterface= RetrofitAdapter.retrofitAdapter().create(RetrofitInterface.class);


    public static Call<DomainModel> addDomain(DomainModel model) {
        return retrofitInterface.putDomain(model);
    }


    public static Call<DomainModel[]> getDomain () {
        return retrofitInterface.getDomain();
    }

}
