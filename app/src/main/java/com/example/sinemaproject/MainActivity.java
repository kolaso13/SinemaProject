package com.example.sinemaproject;

import static com.example.sinemaproject.PreLoadActivity.AllDataList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.sinemaproject.adapter.BannerMoviesPagesAdapter;
import com.example.sinemaproject.adapter.MainRecyclerAdapter;
import com.example.sinemaproject.model.AllCategory;
import com.example.sinemaproject.model.AllData;
import com.example.sinemaproject.model.BannerMovies;
import com.example.sinemaproject.model.CategoryItem;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    BannerMoviesPagesAdapter bannerMoviesPagesAdapter;
    TabLayout indicatorTab, categoryTab;
    ViewPager bannerMoviesViewPager;
    List<BannerMovies> homeBannerList;
    List<BannerMovies> moviesBannerList;
    List<BannerMovies> kidsBannerList;
    List<BannerMovies> tvBannerList;

    SearchView txtSearch;

    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;
    List<AllCategory> allCategoryList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicatorTab = findViewById(R.id.tab_indicator);
        txtSearch= findViewById(R.id.txtSearch);

        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerMovies(1,"The walking dead", "https://i.pinimg.com/originals/91/0d/67/910d67f4f41a771e3f5f0c50c8f8dd0e.jpg", ""));
        homeBannerList.add(new BannerMovies(2,"Star Wars: Andor", "https://www.starwarsnewsnet.com/wp-content/uploads/2022/08/AndorBanner-800x445.jpg", ""));
        homeBannerList.add(new BannerMovies(3,"X-Men", "https://webneel.net/file/images/11-16/8-xmen-movie-poster-design.preview.jpg", ""));
        homeBannerList.add(new BannerMovies(4,"Matrix", "https://www.whatisthematrix.com/assets/images/desktopbanner.jpg", ""));
        homeBannerList.add(new BannerMovies(5,"The Hobbit", "https://alishahussain27.files.wordpress.com/2014/11/the-hobbit-the-desolation-of-smaug-2013-movie-banner-poster.jpg", ""));

        //Banner
        setBannerMoviesPagesAdapter(homeBannerList);
        List<CategoryItem> DramaCatListItem1 = new ArrayList<>();
        List<CategoryItem> ComedyCatListItem1 = new ArrayList<>();
        List<CategoryItem> ActionCatListItem1 = new ArrayList<>();
        List<CategoryItem> AventureCatListItem1 = new ArrayList<>();
        List<CategoryItem> CrimeCatListItem1 = new ArrayList<>();
        List<CategoryItem> ScienceFictionCatListItem1 = new ArrayList<>();
        Log.i("Size", String.valueOf(AllDataList.size()));



        for (AllData item:AllDataList) {
            for(int i=0;i<item.getGenres().length;i++){
                Log.i("Size", String.valueOf(item.getGenres()[i]));
                if(item.getGenres()[i].equalsIgnoreCase("Drama")){
                    DramaCatListItem1.add(new CategoryItem(item.getId(), item.getName(), item.getImageOriginal(), item.getLanguage(), item.getStatus(), item.getPremiered(), item.getEnded(), item.getSummary(), item.getGenres()));
                }
                if(item.getGenres()[i].equalsIgnoreCase("Comedy")){
                    ComedyCatListItem1.add(new CategoryItem(item.getId(), item.getName(), item.getImageOriginal(), item.getLanguage(), item.getStatus(), item.getPremiered(), item.getEnded(), item.getSummary(), item.getGenres()));
                }
                if(item.getGenres()[i].equalsIgnoreCase("Action")){
                    ActionCatListItem1.add(new CategoryItem(item.getId(), item.getName(), item.getImageOriginal(), item.getLanguage(), item.getStatus(), item.getPremiered(), item.getEnded(), item.getSummary(), item.getGenres()));
                }
                if(item.getGenres()[i].equalsIgnoreCase("Adventure")){
                    AventureCatListItem1.add(new CategoryItem(item.getId(), item.getName(), item.getImageOriginal(), item.getLanguage(), item.getStatus(), item.getPremiered(), item.getEnded(), item.getSummary(), item.getGenres()));
                }
                if(item.getGenres()[i].equalsIgnoreCase("Crime")){
                    CrimeCatListItem1.add(new CategoryItem(item.getId(), item.getName(), item.getImageOriginal(), item.getLanguage(), item.getStatus(), item.getPremiered(), item.getEnded(), item.getSummary(), item.getGenres()));
                }
                if(item.getGenres()[i].equalsIgnoreCase("Science-Fiction")){
                    ScienceFictionCatListItem1.add(new CategoryItem(item.getId(), item.getName(), item.getImageOriginal(), item.getLanguage(), item.getStatus(), item.getPremiered(), item.getEnded(), item.getSummary(), item.getGenres()));
                }
            }
        }

        allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory(1, "Drama", DramaCatListItem1));
        allCategoryList.add(new AllCategory(2, "Comedia", ComedyCatListItem1));
        allCategoryList.add(new AllCategory(3, "Acción",ActionCatListItem1));
        allCategoryList.add(new AllCategory(4, "Aventura",AventureCatListItem1));
        allCategoryList.add(new AllCategory(5, "Suspense",CrimeCatListItem1));
        allCategoryList.add(new AllCategory(6, "Ciencia-Ficción",ScienceFictionCatListItem1));

        //Pasamos el array de categorias al recycler
        setMainRecycler(allCategoryList);
    }
    private void setBannerMoviesPagesAdapter(List<BannerMovies> bannerMoviesList){
        bannerMoviesViewPager = findViewById(R.id.banner_viewPager);
        bannerMoviesPagesAdapter = new BannerMoviesPagesAdapter(this, bannerMoviesList);
        bannerMoviesViewPager.setAdapter(bannerMoviesPagesAdapter);
        indicatorTab.setupWithViewPager(bannerMoviesViewPager);

        Timer sliderTimer = new Timer();
        sliderTimer.scheduleAtFixedRate(new AutoSlider(), 4000, 6000);
        indicatorTab.setupWithViewPager(bannerMoviesViewPager, true);
    }

    //Hilo para cambiar de forma automatica el banner
    class AutoSlider extends TimerTask {
        @Override
        public void run(){
            MainActivity.this.runOnUiThread(()->{
                if(bannerMoviesViewPager.getCurrentItem() < homeBannerList.size()-1){
                    bannerMoviesViewPager.setCurrentItem(bannerMoviesViewPager.getCurrentItem() + 1);
                }else{
                    bannerMoviesViewPager.setCurrentItem(0);
                }
            });
        }
    }

    public void setMainRecycler(List<AllCategory> allCategoryList){
        mainRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL, false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, allCategoryList);
        mainRecycler.setAdapter(mainRecyclerAdapter);
    }



}
