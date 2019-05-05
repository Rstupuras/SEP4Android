package com.example.letmebreathe.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.letmebreathe.models.EnvironmentalData;
import com.example.letmebreathe.Repository.EnvironmentalDataRepository;

import java.util.List;

public class CheckEnvironmentalDataViewModel extends ViewModel {


    private MutableLiveData<List<EnvironmentalData>> environmentalData;
    private EnvironmentalDataRepository environmentalDataRepository;
    private String temperature;
    private String CO2;
    private String clasroom;


    public void init() {
        if (environmentalData != null) {
            return;
        }
        environmentalDataRepository = EnvironmentalDataRepository.getInstance();
        environmentalData = environmentalDataRepository.getEnvironmentalData();
        temperature = String.valueOf(environmentalData.getValue().get(environmentalData.getValue().size() - 1).getTemperature())+ '\u00B0';
        CO2 = String.valueOf(environmentalData.getValue().get(environmentalData.getValue().size() - 1).getCO2());
        clasroom = environmentalData.getValue().get(environmentalData.getValue().size() - 1).getLocation();
    }

    public LiveData<List<EnvironmentalData>> getEnvironmentalData() {
        return environmentalData;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getCO2() {
        return CO2;
    }
    public String getClasroom() {
        return clasroom;
    }

}
