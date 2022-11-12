package com.example.sinemaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetails extends AppCompatActivity {

    ImageView movieImage;
    TextView movieName;
    String mName, mImage, mId, mLanguage,mStatus,mPremiered,mEnded,mSummary;
    String[] mGenres;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieImage = findViewById(R.id.movie_image);
        movieName = findViewById(R.id.movie_name);

        mId = getIntent().getStringExtra("movieId");
        mName = getIntent().getStringExtra("movieName");
        mImage = getIntent().getStringExtra("movieImageUrl");

        Glide.with(this).load(mImage).into(movieImage);
        movieName.setText(mName);
    }
}