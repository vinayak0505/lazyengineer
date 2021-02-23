package com.example.android.lazyengineer.jobsAlert.jsonParsing;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.android.lazyengineer.jobsAlert.Jobs;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JobsLoader extends AsyncTaskLoader<List<Jobs>>{

    private URL url;

    public JobsLoader(@NonNull Context context,String url) throws MalformedURLException {
        super(context);
        this.url = new URL(url);
    }

    @Nullable
    @Override
    public List<Jobs> loadInBackground() {
        String jsonResponse;
        ArrayList<Jobs> data = null;
        try {

            jsonResponse = QueryUtilities.makeHttpRequest(url);
            data = QueryUtilities.extractDocument(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
