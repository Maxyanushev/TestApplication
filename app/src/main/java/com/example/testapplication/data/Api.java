package com.example.testapplication.data;

import com.example.testapplication.data.models.UserModel;
import com.example.testapplication.data.models.UserRepos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("users")
    Call<List<UserModel>> getUsers();

    @GET("users/{userName}/repos")
    Call<List<UserRepos>> getRepos(@Path("userName") String userName);
}
