package com.example.testapplication;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }
}
