package com.example.android.lazyengineer.Login;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginUtils {
    private LoginUtils(){}

    public static boolean parseLoginDetail(String jsonResponse) throws JSONException {

        if (TextUtils.isEmpty(jsonResponse)) return false;

        try {

            JSONArray userDetail = new JSONArray(jsonResponse);
            JSONObject currentPdfDocument = userDetail.getJSONObject(0);
            String username = currentPdfDocument.getString("username");
            String firstName = currentPdfDocument.getString("first_name");
            String lastName = currentPdfDocument.getString("last_name");
            String email = currentPdfDocument.getString("email");
            boolean isStaff = currentPdfDocument.getBoolean("is_staff");
            boolean isActive = currentPdfDocument.getBoolean("is_active");
            String dateJoined = currentPdfDocument.getString("date_joined");
            boolean isSuperuser = currentPdfDocument.getBoolean("is_superuser");
            String lastLogin = currentPdfDocument.getString("last_login");
            int id = currentPdfDocument.getInt("id");
            return true;

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
            return false;
        }
    }

    public boolean storeLoginDetail(String jsonResponse){
        return true;
    }
}