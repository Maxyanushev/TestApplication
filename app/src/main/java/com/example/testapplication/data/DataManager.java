package com.example.testapplication.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.testapplication.data.models.UserModel;
import com.example.testapplication.data.models.UserRepos;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;

public class DataManager implements LifecycleObserver {
    public Realm realm;
    private Api service = NetworkModule.getInstance().getService();

    public DataManager(){
        realm = Realm.getDefaultInstance();
    }

    @NonNull
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy() {
        realm.close();
    }

    public Call<List<UserModel>> getUsers() {
        return service.getUsers();
    }

    public Call<List<UserRepos>> getRepos(String userName) {
        return service.getRepos(userName);
    }

    public void saveUsers(List<UserModel> users) {
        realm.executeTransactionAsync(realm -> realm.copyToRealmOrUpdate(users));
    }

    public void saveRepos(List<UserRepos> repos) {
        realm.executeTransactionAsync(realm -> realm.copyToRealmOrUpdate(repos));
    }

    // заполняет List для RV DetailsActivity
    public RealmResults<UserRepos> getListUserRepos() {
        return realm.where(UserRepos.class).isNotNull("name").sort("name").findAll();
    }

    public RealmResults<UserModel> getListUsers() {
        return realm.where(UserModel.class).isNotNull("login").sort("login").findAll();
    }
}
