package com.example.android.lazyengineer.signup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.android.lazyengineer.Login.Login;
import com.example.android.lazyengineer.Notes.QueryUtils;
import com.example.android.lazyengineer.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class SignUpAsyncTask extends AsyncTask<String, Void, String> {
    String jsonResponse;
    private Context context;

    public SignUpAsyncTask (Context context){
        this.context = context;
    }

    @Override
    public String doInBackground(String... urls) {
        URL url = null;
        try {
            url = new URL(urls[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            Log.e(TAG, "doInBackground: responceCode" + responseCode );
            jsonResponse = QueryUtils.readFromStream(urlConnection.getErrorStream());
            Log.e(TAG, "doInBackground: "+ jsonResponse );
            JSONObject jsonArray = new JSONObject(jsonResponse);
            return jsonArray.getString("result");
        }catch (IOException | JSONException games){
            return "unable to connect to internet";
        }
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        TextView txt = (TextView)((Activity) context).findViewById(R.id.SignUpNotice);
        txt.setText(s);
        if(s.equals("Sign up Successful, verify your account through mail id")) {
            Intent intent = new Intent(context, Login.class);
            context.startActivity(intent);
            signup.activity.finish();
        }
    }
}
