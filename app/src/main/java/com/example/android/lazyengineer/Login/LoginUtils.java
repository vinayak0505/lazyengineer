package com.example.android.lazyengineer.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.example.android.lazyengineer.Settings.SettingsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginUtils {
    private LoginUtils() {
    }

    public static String parseLoginDetail(String jsonResponse) throws JSONException {
        if (TextUtils.isEmpty(jsonResponse)) return null;

        try {
            JSONArray user = new JSONArray(jsonResponse);
            JSONObject userDetail = user.getJSONObject(0);
            String username = userDetail.getString("username");
            String firstName = userDetail.getString("first_name");
            String lastName = userDetail.getString("last_name");
            String email = userDetail.getString("email");
            boolean isStaff = userDetail.getBoolean("is_staff");
            boolean isActive = userDetail.getBoolean("is_active");
            String dateJoined = userDetail.getString("date_joined");
            boolean isSuperuser = userDetail.getBoolean("is_superuser");
            String lastLogin = userDetail.getString("last_login");
            int id = userDetail.getInt("id");
            return username;
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
            return null;
        }
    }
}