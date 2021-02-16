package com.example.android.lazyengineer.Notes.Document;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.android.lazyengineer.Notes.Document.PdfDocument;
import com.example.android.lazyengineer.Notes.QueryUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PdfLoader extends AsyncTaskLoader<List<PdfDocument>> {

    private static String MAIN_URL;

    public PdfLoader(@NonNull Context context,String url) {
        super(context);
        MAIN_URL = url;
    }

    @Nullable
    @Override
    public List<PdfDocument> loadInBackground() {
        URL url = QueryUtils.createUrl(MAIN_URL);
        String jsonResponse;
        ArrayList<PdfDocument> data = null;
        try {
            assert url != null;
            jsonResponse = QueryUtils.makeHttpRequest(url);
            data = QueryUtils.extractDocument(jsonResponse);
        } catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }
}
