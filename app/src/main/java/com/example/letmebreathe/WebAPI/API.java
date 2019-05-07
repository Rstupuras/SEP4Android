package com.example.letmebreathe.WebAPI;

import com.example.letmebreathe.models.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("accounts")
    Call<List<Account>> getAccounts();
}
