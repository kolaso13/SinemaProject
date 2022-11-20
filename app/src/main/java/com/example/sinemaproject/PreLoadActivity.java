package com.example.sinemaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sinemaproject.model.AllData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PreLoadActivity extends AppCompatActivity {
    static List<AllData> AllDataList = new ArrayList<AllData>();

    public static ArrayList<AllData> listaCompleta = new ArrayList<>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_load);
        progressBar = findViewById(R.id.progressBar);
        new Task1().execute();
    }

    class Task1 extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            LeerApi();
        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(PreLoadActivity.this, MainActivity.class);
            PreLoadActivity.this.startActivity(intent);
        }
    }
        private void LeerApi() {
        String url = "https://api.tvmaze.com/shows";
        StringRequest postResquest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray json = new JSONArray(response);
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject Object = json.getJSONObject(i);
                        Integer id = Object.getInt("id");
                        String name = Object.getString("name");
                        String language = Object.getString("language");
                        String status = Object.getString("status");
                        String premiered = Object.getString("premiered");
                        String ended = Object.getString("ended");
                        String summary = Object.getString("summary");
                        JSONArray genres = Object.getJSONArray("genres");

                        ArrayList<String> exampleList = new ArrayList<String>();
                        for (int j = 0; j < genres.length(); j++) {
                            exampleList.add((String) genres.get(j));
                        }
                        int size = exampleList.size();
                        String[] GenresstringArray = exampleList.toArray(new String[size]);

                        String rating = Object.getJSONObject("rating").getString("average").toString() == null ? "0.0" : Object.getJSONObject("rating").getString("average");
                        String imageOriginal = Object.getJSONObject("image").getString("original");
                        String imageMedium= Object.getJSONObject("image").getString("medium");
                        AllData allData = new AllData(id,name,language,status,premiered,ended,summary,GenresstringArray,rating,imageOriginal,imageMedium);
                        AllDataList.add(allData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(postResquest);
    }
}