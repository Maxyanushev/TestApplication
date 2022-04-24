package com.example.testapplication.ui.activities.main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testapplication.data.DataManager;
import com.example.testapplication.data.models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<UserModel>> users = new MutableLiveData<>();
    private DataManager dataManager;

    public MainActivityViewModel() {
        dataManager = new DataManager();
    }

    public void getUsers() {
        dataManager.getUsers().enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserModel>> call, @NonNull Response<List<UserModel>> response) {
                if (response.body() != null) {
                    List<UserModel> userModels = response.body();
                    dataManager.saveUsers(userModels);
                    users.postValue(userModels);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<UserModel>> call, @NonNull Throwable t) {
                users.postValue(dataManager.getListUsers());
                Log.e("Error", t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<List<UserModel>> getUsersLiveData(){
        return users;
    }
}
