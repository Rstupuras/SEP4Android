package com.example.letmebreathe.WebAPI;

import com.example.letmebreathe.models.Account;
import com.example.letmebreathe.models.EnvironmentalData;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {
    @GET("accounts")
    Call<ArrayList<Account>> getAccounts();

    @PUT("accounts/{username}")
    Call<Void> updateAccount(@Path("username") String username, @Body Account account);

    @POST("accounts")
    Call<Void> addAccount(@Body Account account);

    @DELETE("accounts/{username}")
    Call<Void> deleteAccount(@Path("username") String username);

    @GET("environmentaldata")
    Call<ArrayList<EnvironmentalData>> getEnvironmentalData();


}
