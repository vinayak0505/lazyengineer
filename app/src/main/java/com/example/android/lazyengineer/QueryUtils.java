package com.example.android.lazyengineer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.example.android.lazyengineer.data.DocumentContract;
import com.example.android.lazyengineer.data.DocumentdbHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class QueryUtils  {
    private QueryUtils(){}

    public static ArrayList<PdfDocument> extractDocument(String documentJSON){
        if (TextUtils.isEmpty(documentJSON)) return null;

        ArrayList<PdfDocument> pdfDocuments = new ArrayList<>();
        try {

            JSONArray documentArray = new JSONArray(documentJSON);

            for(int i = 0; i< documentArray.length();i++){
                JSONObject currentPdfDocument = documentArray.getJSONObject(i);
                String Title = currentPdfDocument.getString("subject");
                String description = currentPdfDocument.getString("description");
                String postdate = currentPdfDocument.getString("postdate").substring(0,10);
                String documentUrl = currentPdfDocument.getString("url");
                int id = currentPdfDocument.getInt("id");
                PdfDocument pdfDocument = new PdfDocument(documentUrl,Title,description,postdate,id);
                pdfDocuments.add(pdfDocument);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return pdfDocuments;

    }
    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(20000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else {
                Log.e(TAG, "makeHttpRequest: urlConnection error code = "+urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(TAG, "makeHttpRequest: ioException"+e);
            return null;
        }
        finally {
            if (urlConnection!=null) urlConnection.disconnect();
            if (inputStream!=null) inputStream.close();
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line!=null){
                output.append(line);
                line=reader.readLine();
            }
        }
        return output.toString();
    }



    static URL createUrl(String StringUrl){
        URL url = null;
        try{
            url =  new URL(StringUrl);
            return url;
        } catch (MalformedURLException e) {
            Log.e(TAG, "createUrl: malformedUrlException" +e);
        }
        return null;
    }
}
