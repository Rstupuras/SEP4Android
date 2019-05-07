package com.example.letmebreathe.WebAPI;

import com.example.letmebreathe.models.Account;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {
    @GET("accounts")
    Call<ArrayList<Account>> getAccounts();

    @PUT("accounts/{username}")
    Call<Void> updateAccount(@Path("username") String username, @Body Account account);
}
