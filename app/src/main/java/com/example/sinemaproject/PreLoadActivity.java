package com.example.sinemaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sinemaproject.model.AllData;

import java.util.ArrayList;

public class PreLoadActivity extends AppCompatActivity {

    public static ArrayList<AllData> listaCompleta = new ArrayList<>();
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_load);
        progressBar = findViewById(R.id.progressBar);

        new Task1().execute();
    }

    class Task1 extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(5000);
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
}