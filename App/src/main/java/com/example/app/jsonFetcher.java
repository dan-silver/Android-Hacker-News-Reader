package com.example.app;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dan on 11/24/13.
 */
public class jsonFetcher {
    private static final String TAG = "Silver";
    private String URL;

    public jsonFetcher(String URL) {
        this.URL = URL;
    }

    public JSONArray fetchJSON() {
        Log.v(TAG, "Starting JSON fetching...");
        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
        HttpPost httppost = new HttpPost(URL);

        httppost.setHeader("Content-type", "application/json");

        InputStream inputStream = null;
        JSONObject jObject = null;
        try {
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            inputStream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            jObject = new JSONObject(sb.toString());
        } catch (Exception e) {
            Log.v(TAG, e.toString());
        } finally {
            try {
                if (inputStream != null) inputStream.close();

            } catch (Exception squish) {

            }
        }
        try {
            return jObject.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}