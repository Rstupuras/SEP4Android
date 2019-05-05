package com.example.letmebreathe.Repository;

import android.arch.lifecycle.MutableLiveData;

import com.example.letmebreathe.Database.MockData;
import com.example.letmebreathe.models.EnvironmentalData;

import java.util.ArrayList;
import java.util.List;

public class EnvironmentalDataRepository {
    private static EnvironmentalDataRepository instance;
    private ArrayList<EnvironmentalData> environmentalData = new ArrayList<>();
    private MockData data = MockData.getInstance();

    public static EnvironmentalDataRepository getInstance() {
        if (instance == null) {
            instance = new EnvironmentalDataRepository();
        }
        return instance;
    }

    public MutableLiveData<List<EnvironmentalData>> getEnvironmentalData() {
        setEnvironmentalData();
        MutableLiveData<List<EnvironmentalData>> data = new MutableLiveData<>();
        data.setValue(environmentalData);
        return data;
    }

    public void setEnvironmentalData() {
        environmentalData = data.getData();
    }

}
