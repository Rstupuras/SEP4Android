package com.example.letmebreathe;

import android.os.AsyncTask;

import com.example.letmebreathe.WebAPI.API;
import com.example.letmebreathe.models.EnvironmentalData;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnvironmentalDataTask extends AsyncTask<Void,Void,ArrayList<EnvironmentalData>> {
    private Retrofit retrofit;
    private API api;


    @Override
    protected ArrayList<EnvironmentalData> doInBackground(Void... voids) {
        retrofit = new Retrofit.Builder().baseUrl("https://sep4dataapi.azurewebsites.net/api/").addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(API.class);
        final Call<ArrayList<EnvironmentalData>> call = api.getEnvironmentalData();
        try {

            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
