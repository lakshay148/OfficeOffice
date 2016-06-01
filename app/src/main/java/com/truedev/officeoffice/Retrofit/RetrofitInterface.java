package com.truedev.officeoffice.Retrofit;

import com.truedev.officeoffice.Model.DomainModel;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by dipanshugarg on 2/6/16.
 */
public interface RetrofitInterface  {

    @POST("/apis/domains")
    Call<DomainModel> putDomain(@Body DomainModel model);


    @GET("/apis/domains")
    Call<DomainModel[]> getDomain();
}
