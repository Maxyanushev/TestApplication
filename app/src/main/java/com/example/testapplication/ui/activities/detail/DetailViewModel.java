package com.example.testapplication.ui.activities.detail;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testapplication.data.DataManager;
import com.example.testapplication.data.models.UserRepos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends ViewModel {
    private MutableLiveData<List<UserRepos>> repos = new MutableLiveData<>();
    private DataManager dataManager;

    public DetailViewModel() {
        dataManager = new DataManager();
    }

    public void getRepos(String userName) {
        dataManager.getRepos(userName).enqueue(new Callback<List<UserRepos>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserRepos>> call, @NonNull Response<List<UserRepos>> response) {
                if (response.body() != null) {
                    List<UserRepos> reps = response.body();
                    dataManager.saveRepos(reps);
                    repos.postValue(reps);
                }
            }
            @Override
            public void onFailure(Call<List<UserRepos>> call, Throwable t) {
                repos.postValue(dataManager.getListUserRepos());
                Log.e("Error", t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<List<UserRepos>> getReposLiveData(){
        return repos;
    }
}
