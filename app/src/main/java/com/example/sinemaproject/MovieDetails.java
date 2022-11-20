package com.example.sinemaproject;

import static com.example.sinemaproject.MainActivity.FavoriteMovies;
import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.Arrays;

public class MovieDetails extends AppCompatActivity {

    ImageView movieImage, favorite;
    TextView movieName, movieLanguage, movieStatus, moviePremiered, movieEnded, movieSummary, moviegenres, movieRating;
    String mName;
    String mImage;
    int mId;
    String mLanguage;
    String mStatus;
    String mPremiered;
    String mEnded;
    String mSummary;
    String mRating;
    String[] mGenres;
    boolean mfav;

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
        movieRating = findViewById(R.id.rating);
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
        mRating = extras.getString("movieRating");
        mfav = extras.getBoolean("fav");


        if(mfav){
            Drawable img = favorite.getResources().getDrawable(R.drawable.ic_action_favorite);
            favorite.setImageDrawable(img);
        }else if(!mfav){
            Drawable img = favorite.getResources().getDrawable(R.drawable.ic_action_nofavorite);
            favorite.setImageDrawable(img);
        }
        Glide.with(this).load(mImage).into(movieImage);
        movieName.setText(mName);
        movieLanguage.setText(mLanguage);
        movieStatus.setText(mStatus);
        moviePremiered.setText(mPremiered);
        movieEnded.setText(mEnded);
        movieSummary.setText(mSummary.replaceAll("<[^>]*>", ""));
        movieRating.setText(mRating);
        moviegenres.setText(Arrays.toString(mGenres).replaceAll("\\[|\\]", ""));

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FavoriteMovies.isEmpty()) {
                    Drawable img = favorite.getResources().getDrawable(R.drawable.ic_action_favorite);
                    favorite.setImageDrawable(img);
                    FavoriteMovies.add(mName);
                }else{
                    if (FavoriteMovies.contains(mName)) {
                        Drawable img = favorite.getResources().getDrawable(R.drawable.ic_action_nofavorite);
                        favorite.setImageDrawable(img);
                        FavoriteMovies.remove(FavoriteMovies.indexOf(mName));
                    } else {
                        Drawable img = favorite.getResources().getDrawable(R.drawable.ic_action_favorite);
                        favorite.setImageDrawable(img);
                        FavoriteMovies.add(mName);
                    }
                }
                for (int i = 0; i < FavoriteMovies.size(); i++) {
                    Log.i("FavoriteMovies", String.valueOf(FavoriteMovies.get(i)));
                }
                saveData();
            }
        });
    }

    //Guardamos los favoritos en Shared Pref
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(FavoriteMovies);

        editor.putString("courses", json);

        editor.apply();

//        editor.clear().apply();
    }
}