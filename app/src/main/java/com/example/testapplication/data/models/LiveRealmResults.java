package com.example.testapplication.data.models;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class LiveRealmResults<T extends RealmModel> extends LiveData<List<T>> {
    private final OrderedRealmCollectionChangeListener<RealmResults<T>> listener = (ts, orderedCollectionChangeSet) -> setValue(ts);

    private final RealmResults<T> results;

    public LiveRealmResults(RealmResults<T> results){
        this.results = results;
        if (results.isLoaded()) {
            // we should not notify observers when results aren't ready yet (async query).
            // however, synchronous query should be set explicitly.
            setValue(results);
        }
    }

    @Override
    protected void onActive() {
        super.onActive();
        if (results.isValid()) { // invalidated results can no longer be observed.
            results.addChangeListener(listener);
        }
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (results.isValid()) {
            results.removeChangeListener(listener);
        }
    }
}
