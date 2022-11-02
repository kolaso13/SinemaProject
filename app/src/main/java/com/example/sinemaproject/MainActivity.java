package com.example.sinemaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.example.sinemaproject.adapter.BannerMoviesPagesAdapter;
import com.example.sinemaproject.model.BannerMovies;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BannerMoviesPagesAdapter bannerMoviesPagesAdapter;
    TabLayout tabLayout;
    ViewPager bannerMoviesViewPager;
    List<BannerMovies> bannerMoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_indicator);
        bannerMoviesList = new ArrayList<>();
        bannerMoviesList.add(new BannerMovies(1,"The walking dead", "https://i.pinimg.com/originals/91/0d/67/910d67f4f41a771e3f5f0c50c8f8dd0e.jpg", ""));
        bannerMoviesList.add(new BannerMovies(2,"Star Wars: Andor", "https://www.starwarsnewsnet.com/wp-content/uploads/2022/08/AndorBanner-800x445.jpg", ""));
        bannerMoviesList.add(new BannerMovies(3,"X-Men", "https://webneel.net/file/images/11-16/8-xmen-movie-poster-design.preview.jpg", ""));
        bannerMoviesList.add(new BannerMovies(4,"Matrix", "https://www.whatisthematrix.com/assets/images/desktopbanner.jpg", ""));
        bannerMoviesList.add(new BannerMovies(5,"The Hobbit", "https://alishahussain27.files.wordpress.com/2014/11/the-hobbit-the-desolation-of-smaug-2013-movie-banner-poster.jpg", ""));

        setBannerMoviesPagesAdapter(bannerMoviesList);
    }
    private void setBannerMoviesPagesAdapter(List<BannerMovies> bannerMoviesList){
        bannerMoviesViewPager = findViewById(R.id.banner_viewPager);
        bannerMoviesPagesAdapter = new BannerMoviesPagesAdapter(this, bannerMoviesList);
        bannerMoviesViewPager.setAdapter(bannerMoviesPagesAdapter);
        tabLayout.setupWithViewPager(bannerMoviesViewPager);

    }
}