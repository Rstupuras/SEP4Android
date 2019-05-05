package com.example.letmebreathe.View;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.letmebreathe.BR;
import com.example.letmebreathe.R;
import com.example.letmebreathe.databinding.EvironmentalDataAcitivityBinding;
import com.example.letmebreathe.models.EnvironmentalData;
import com.example.letmebreathe.viewModels.CheckEnvironmentalDataViewModel;

import java.util.ArrayList;

public class CheckEnvironmentalDataActivity extends AppCompatActivity {

    private CheckEnvironmentalDataViewModel checkEnvironmentalDataViewModel;

    private ArrayList<EnvironmentalData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EvironmentalDataAcitivityBinding binding = DataBindingUtil.setContentView(this, R.layout.evironmental_data_acitivity);
        binding.setLifecycleOwner(this);

        checkEnvironmentalDataViewModel = ViewModelProviders.of(this).get(CheckEnvironmentalDataViewModel.class);
        checkEnvironmentalDataViewModel.init();
        binding.setVariable(BR.data, checkEnvironmentalDataViewModel);

    }
}
