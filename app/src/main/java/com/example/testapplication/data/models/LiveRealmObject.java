package com.example.testapplication.data.models;

import androidx.lifecycle.MutableLiveData;

import java.util.Objects;

import io.realm.RealmObject;
import io.realm.RealmObjectChangeListener;

public class LiveRealmObject <T extends RealmObject> extends MutableLiveData<T> {
    private final RealmObjectChangeListener<T> listener = (obj, changeSet) -> {
        if (Objects.requireNonNull(changeSet).isDeleted()) {
            setValue(obj);
        } else {
            setValue(null);
        }
    };
    private final T value;
    public LiveRealmObject(T value){
        this.value = value;
    }

    @Override
    protected void onActive() {
        super.onActive();
        T obj = value;
        if (RealmObject.isValid(obj)) {
            RealmObject.addChangeListener(obj, listener);
        }
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        T obj = value;
        if (RealmObject.isValid(obj)) {
            RealmObject.removeChangeListener(obj, listener);
        }
    }
}
