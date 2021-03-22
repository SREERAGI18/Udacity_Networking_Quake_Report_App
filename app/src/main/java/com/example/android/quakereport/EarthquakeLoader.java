package com.example.android.quakereport;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private final String strings;

    public EarthquakeLoader(@NonNull Context context, String url) {
        super(context);

        this.strings = url;
    }

    @Override
    protected void onStartLoading() {
        Log.v("Where? ", "At onStartLoading");
        forceLoad();
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        Log.v("Where? ", "At loadInBackground");
        List<Earthquake> earthquakes = new ArrayList<>();

        if(strings == null){
            return null;
        }

        try {
            earthquakes = QueryUtils.extractEarthquakes(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return earthquakes;
    }
}
