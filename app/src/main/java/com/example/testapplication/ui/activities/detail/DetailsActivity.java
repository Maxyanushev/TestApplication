package com.example.testapplication.ui.activities.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.testapplication.R;
import com.example.testapplication.ui.adapters.UserReposAdapter;

public class DetailsActivity extends AppCompatActivity {
    public static final String USERNAME = "USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        DetailViewModel viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        String username = getIntent().getStringExtra(USERNAME);
        viewModel.getRepos(username);
        RecyclerView recyclerViewUserRepos = findViewById(R.id.rv_userRepos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewUserRepos.setLayoutManager(layoutManager);

        recyclerViewUserRepos.setHasFixedSize(true);
        // помещаем наш List в адаптер для RecyclerView
        UserReposAdapter userReposAdapter = new UserReposAdapter(); //
        recyclerViewUserRepos.setAdapter(userReposAdapter);
        viewModel.getReposLiveData().observe(this, userReposAdapter::setList);
    }

}