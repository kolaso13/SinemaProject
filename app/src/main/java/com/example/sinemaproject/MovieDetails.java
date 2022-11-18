package com.example.sinemaproject;

import static com.example.sinemaproject.MainActivity.FavoriteMovies;
import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Arrays;

public class MovieDetails extends AppCompatActivity {

    ImageView movieImage, favorite;
    TextView movieName, movieLanguage, movieStatus, moviePremiered, movieEnded, movieSummary, moviegenres;
    String mName;
    String mImage;
    int mId;
    String mLanguage;
    String mStatus;
    String mPremiered;
    String mEnded;
    String mSummary;
    String[] mGenres;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        favorite = findViewById(R.id.imgFavorite);
        movieImage = findViewById(R.id.movie_image);
        movieName = findViewById(R.id.movie_name);
        movieLanguage = findViewById(R.id.language);
        movieStatus = findViewById(R.id.status);
        moviePremiered = findViewById(R.id.premiered);
        movieEnded = findViewById(R.id.ended);
        movieSummary = findViewById(R.id.Summary);
        moviegenres = findViewById(R.id.genres);

        Bundle extras = getIntent().getExtras();

        mId = extras.getInt("movieId");
        mName = extras.getString("movieName");
        mImage = extras.getString("movieImageUrl");
        mLanguage = extras.getString("movieLanguage");
        mStatus = extras.getString("movieStatus");
        mPremiered = extras.getString("moviePremiered");
        mEnded = extras.getString("movieEnded");
        mSummary = extras.getString("movieSummary");
        mGenres = extras.getStringArray("movieGenres");

        Glide.with(this).load(mImage).into(movieImage);
        movieName.setText(mName);
        movieLanguage.setText(mLanguage);
        movieStatus.setText(mStatus);
        moviePremiered.setText(mPremiered);
        movieEnded.setText(mEnded);
        movieSummary.setText(mSummary.replaceAll("<[^>]*>", ""));
        moviegenres.setText(Arrays.toString(mGenres).replaceAll("\\[|\\]", ""));

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Size", String.valueOf(FavoriteMovies.size()));
                if(FavoriteMovies.isEmpty()) {
                    Log.i("Hola", "Fu");
                    Drawable img = favorite.getResources().getDrawable(R.drawable.ic_action_favorite);
                    favorite.setImageDrawable(img);
                    FavoriteMovies.add(mId);
                }else{
                    for (int i = 0; i < FavoriteMovies.size(); i++) {
                        Log.i("", "");
                        if (FavoriteMovies.get(i) == mId) {
                            Drawable img = favorite.getResources().getDrawable(R.drawable.ic_action_nofavorite);
                            favorite.setImageDrawable(img);
                            FavoriteMovies.remove(i);
                        } else {
                            Drawable img = favorite.getResources().getDrawable(R.drawable.ic_action_favorite);
                            favorite.setImageDrawable(img);
                            FavoriteMovies.add(mId);
                        }
                    }
                }
            }
        });
    }
}