package com.example.android.lazyengineer;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PdfLoader extends AsyncTaskLoader<List<PdfDocument>> {

    public static String MAIN_URL = "";

    public PdfLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<PdfDocument> loadInBackground() {
        URL url = QueryUtils.createUrl(MAIN_URL);
        String jsonResponse = "";
        ArrayList<PdfDocument> data = null;
        try {
            jsonResponse = QueryUtils.makeHttpRequest(url);
            data = QueryUtils.extractDocument(jsonResponse);
        } catch (IOException e){

        }
        return data;
    }
}
