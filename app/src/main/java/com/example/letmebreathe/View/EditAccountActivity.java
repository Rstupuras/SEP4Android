package com.example.letmebreathe.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.letmebreathe.BR;
import com.example.letmebreathe.R;
import com.example.letmebreathe.databinding.ActivityEditAccountBinding;
import com.example.letmebreathe.models.Account;
import com.example.letmebreathe.viewModels.CheckEnvironmentalDataViewModel;
import com.example.letmebreathe.viewModels.EditAccountViewModel;

public class EditAccountActivity extends AppCompatActivity {
    private EditAccountViewModel editAccountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        ActivityEditAccountBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_account);
        binding.setLifecycleOwner(EditAccountActivity.this);
        editAccountViewModel = ViewModelProviders.of(this).get(EditAccountViewModel.class);
        editAccountViewModel.init();
        binding.setVariable(BR.data, editAccountViewModel);
//        Intent intent = getIntent();
//
//        Bundle extras = intent.getExtras();
//
//        int id = extras.getInt("account");
//        editAccountViewModel.setAccount(id);

    }
}
