package com.example.android.lazyengineer.Login;

import com.example.android.lazyengineer.MainActivity;
import com.example.android.lazyengineer.QueryUtils;
import com.example.android.lazyengineer.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginAsyncTask extends AsyncTask<String, Void, String> {
    String jsonResponse;
    private Context context;

    public LoginAsyncTask (Context context){
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
            if (responseCode == 200){
                jsonResponse = QueryUtils.makeHttpRequest(url);
                if(LoginUtils.parseLoginDetail(jsonResponse)){
                    Login.check = true;
                    return "Login successful";
                }
            }
            else if(responseCode == 404){
                return "Wrong user name or password";
            }
        }catch (IOException | JSONException io){
            return "unable to connect to internet";
        }
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        TextView txt = (TextView)((Activity) context).findViewById(R.id.Notice);
        txt.setText(s);
        if(s.equals("Login successful")) {
            Intent downloadIntent = new Intent(context, MainActivity.class);
            context.startActivity(downloadIntent);
            Login.status = false;
            Login.activity.finish();
        }
    }
}
