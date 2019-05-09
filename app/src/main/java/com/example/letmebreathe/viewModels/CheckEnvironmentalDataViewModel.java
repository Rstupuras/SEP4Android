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
        environmentalData = environmentalDataRepository.getEnvironmentalDataStored();

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

    public void setData(int id) {
        temperature = String.valueOf(environmentalData.getValue().get(id).getTemperature()) + '\u00B0'+ " C";
        CO2 = String.valueOf(environmentalData.getValue().get(id).getCO2());
        clasroom = environmentalData.getValue().get(id).getLocation();
    }


    public void setDataFahrenheit(int id) {
        CO2 = String.valueOf(environmentalData.getValue().get(id).getCO2());
        clasroom = environmentalData.getValue().get(id).getLocation();
        double temperaturetmp = Double.parseDouble(String.valueOf(environmentalData.getValue().get(id).getTemperature()));
        double temperaturefnh = ((temperaturetmp*9)/5) + 32;
        temperature = temperaturefnh + "" + '\u00B0' + " F";
    }
}
