package com.example.testapplication.ui.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.testapplication.R;
import com.example.testapplication.data.models.UserRepos;
import com.example.testapplication.ui.activities.detail.DetailsActivity;
import com.example.testapplication.ui.adapters.GitUserAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewGitUsers;
    private GitUserAdapter gitUserAdapter;

    private MainActivityViewModel mainActivityViewModel;

    public static ArrayList<UserRepos> getUserReposList() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getUsers();
        recyclerViewGitUsers = findViewById(R.id.rv_gitUsers);
        recyclerViewGitUsers.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewGitUsers.setHasFixedSize(true);
        gitUserAdapter = new GitUserAdapter(gitUser -> startActivity(new Intent(this, DetailsActivity.class).putExtra(DetailsActivity.USERNAME, gitUser.getLogin())));
        recyclerViewGitUsers.setAdapter(gitUserAdapter);

        mainActivityViewModel.getUsersLiveData().observe(this, userModels -> {
            if (userModels != null) {
                gitUserAdapter.setUsers(userModels);
            }
        });
    }
}