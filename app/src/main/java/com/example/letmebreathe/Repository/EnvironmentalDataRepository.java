package com.example.letmebreathe.Repository;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.letmebreathe.WebAPI.ApiConsumer;
import com.example.letmebreathe.models.EnvironmentalData;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class EnvironmentalDataRepository {
    private static EnvironmentalDataRepository instance;
    private ArrayList<EnvironmentalData> environmentalDataStored = new ArrayList<>();
    private ArrayList<EnvironmentalData> environmentalDataFromAPI = new ArrayList<>();
    private ApiConsumer data = ApiConsumer.getInstance();

    public static EnvironmentalDataRepository getInstance() {
        if (instance == null) {
            instance = new EnvironmentalDataRepository();
        }
        return instance;
    }


    public MutableLiveData<List<EnvironmentalData>> getEnvironmentalDataStored() {
        setEnvironmentalDataStored();
        MutableLiveData<List<EnvironmentalData>> data = new MutableLiveData<>();
        data.setValue(environmentalDataStored);
        return data;
    }

    public void setEnvironmentalDataStored() {

        environmentalDataStored = (ArrayList<EnvironmentalData>) data.getEnvironmentalDataStored();
    }

    public MutableLiveData<List<EnvironmentalData>> getEnvironmentalDataFromAPI() {
        setEnvironmentalDataFromAPI();
        MutableLiveData<List<EnvironmentalData>> data = new MutableLiveData<>();
        data.setValue(environmentalDataFromAPI);
        return data;

    }


    public void setEnvironmentalDataFromAPI() {


        this.environmentalDataFromAPI = (ArrayList<EnvironmentalData>) data.getEnvironmentalFromAPI();
    }


}
