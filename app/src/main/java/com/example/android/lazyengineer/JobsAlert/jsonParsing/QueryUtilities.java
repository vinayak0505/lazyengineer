package com.example.android.lazyengineer.JobsAlert.jsonParsing;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class QueryUtilities {
    private QueryUtilities() {
    }

    public static ArrayList<Jobs> extractDocument(String documentJSON) {
        if (TextUtils.isEmpty(documentJSON)) return null;

        ArrayList<Jobs> jobs = new ArrayList<>();
        try {

            JSONArray documentArray = new JSONArray(documentJSON);

            for (int i = 0; i < documentArray.length(); i++) {
                JSONObject currentJob = documentArray.getJSONObject(i);
                String id = currentJob.getString("id");
                String Title = currentJob.getString("title");
                String role = currentJob.getString("jobrole");
                String location = currentJob.getString("location");
                String applyUrl = currentJob.getString("applyurl");
                String salary = currentJob.getString("salary");
                Jobs job = new Jobs(id, Title, role, location, salary, applyUrl);
                jobs.add(job);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return jobs;

    }

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(20000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(TAG, "makeHttpRequest: urlConnection error code = " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(TAG, "makeHttpRequest: ioException" + e);
            return null;
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (inputStream != null) inputStream.close();
        }
        return jsonResponse;
    }

    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
