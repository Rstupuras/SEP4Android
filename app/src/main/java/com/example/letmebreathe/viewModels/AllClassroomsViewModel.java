package com.example.letmebreathe.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.letmebreathe.Repository.EnvironmentalDataRepository;
import com.example.letmebreathe.models.EnvironmentalData;

import java.util.List;

public class AllClassroomsViewModel extends ViewModel {
    private MutableLiveData<List<EnvironmentalData>> environmentalDataList;
    private EnvironmentalDataRepository repo;

    public void init(){
        if (environmentalDataList != null){
            return;
        }
        repo = EnvironmentalDataRepository.getInstance();
        environmentalDataList = repo.getEnvironmentalDataFromAPI();
    }

    public LiveData<List<EnvironmentalData>> getEnvironmentalDataList(){
        return environmentalDataList;
    }
}
