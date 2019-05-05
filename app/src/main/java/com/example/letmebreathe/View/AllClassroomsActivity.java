package com.example.letmebreathe.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.letmebreathe.R;
import com.example.letmebreathe.adapters.RecyclerAdapter;
import com.example.letmebreathe.models.EnvironmentalData;
import com.example.letmebreathe.viewModels.AllClassroomsViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AllClassroomsActivity extends AppCompatActivity implements RecyclerAdapter.OnListItemClickListener {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private AllClassroomsViewModel allClassroomsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allclassrooms);
        recyclerView = findViewById(R.id.rv);

        allClassroomsViewModel = ViewModelProviders.of(this).get(AllClassroomsViewModel.class);

        allClassroomsViewModel.init();

        allClassroomsViewModel.getEnvironmentalDataList().observe(this, new Observer<List<EnvironmentalData>>() {
            @Override
            public void onChanged(@Nullable List<EnvironmentalData> environmentalData) {
                adapter.notifyDataSetChanged();
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new RecyclerAdapter(this, (ArrayList<EnvironmentalData>) allClassroomsViewModel.getEnvironmentalDataList().getValue(), this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
    Intent intent = new Intent(AllClassroomsActivity.this, CheckEnvironmentalDataActivity.class);
    intent.putExtra("environmentalData", (Serializable) adapter.getEnvironmentalDataList().get(clickedItemIndex));
    startActivity(intent);
    }
}
