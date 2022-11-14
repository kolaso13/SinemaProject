package com.example.sinemaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Arrays;

public class MovieDetails extends AppCompatActivity {

    ImageView movieImage;
    TextView movieName, movieLanguage, movieStatus, moviePremiered, movieEnded, movieSummary, moviegenres;
    String mName, mImage, mId, mLanguage,mStatus,mPremiered,mEnded,mSummary;
    String[] mGenres;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieImage = findViewById(R.id.movie_image);
        movieName = findViewById(R.id.movie_name);
        movieLanguage = findViewById(R.id.language);
        movieStatus = findViewById(R.id.status);
        moviePremiered = findViewById(R.id.premiered);
        movieEnded = findViewById(R.id.ended);
        movieSummary = findViewById(R.id.Summary);
        moviegenres = findViewById(R.id.genres);


        mId = getIntent().getStringExtra("movieId");
        mName = getIntent().getStringExtra("movieName");
        mImage = getIntent().getStringExtra("movieImageUrl");
        mLanguage = getIntent().getStringExtra("movieLanguage");
        mStatus = getIntent().getStringExtra("movieStatus");
        mPremiered = getIntent().getStringExtra("moviePremiered");
        mEnded = getIntent().getStringExtra("movieEnded");
        mSummary = getIntent().getStringExtra("movieSummary");
        mGenres = getIntent().getStringArrayExtra("movieGenres");

        Glide.with(this).load(mImage).into(movieImage);
        movieName.setText(mName);
        movieLanguage.setText(mLanguage);
        movieStatus.setText(mStatus);
        moviePremiered.setText(mPremiered);
        movieEnded.setText(mEnded);
        movieSummary.setText(mSummary.replaceAll("<[^>]*>", ""));
        moviegenres.setText(Arrays.toString(mGenres).replaceAll("\\[|\\]", ""));
    }
}