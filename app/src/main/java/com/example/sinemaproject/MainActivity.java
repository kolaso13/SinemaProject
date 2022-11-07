package com.example.sinemaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import java.util.ArrayList;
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
    static List<AllData> AllDataList = new ArrayList<AllData>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LeerApi();
        indicatorTab = findViewById(R.id.tab_indicator);
        categoryTab = findViewById(R.id.tablayout);
        txtSearch= findViewById(R.id.txtSearch);

        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerMovies(1,"The walking dead", "https://i.pinimg.com/originals/91/0d/67/910d67f4f41a771e3f5f0c50c8f8dd0e.jpg", ""));
        homeBannerList.add(new BannerMovies(2,"Star Wars: Andor", "https://www.starwarsnewsnet.com/wp-content/uploads/2022/08/AndorBanner-800x445.jpg", ""));
        homeBannerList.add(new BannerMovies(3,"X-Men", "https://webneel.net/file/images/11-16/8-xmen-movie-poster-design.preview.jpg", ""));
        homeBannerList.add(new BannerMovies(4,"Matrix", "https://www.whatisthematrix.com/assets/images/desktopbanner.jpg", ""));
        homeBannerList.add(new BannerMovies(5,"The Hobbit", "https://alishahussain27.files.wordpress.com/2014/11/the-hobbit-the-desolation-of-smaug-2013-movie-banner-poster.jpg", ""));

        tvBannerList = new ArrayList<>();
        tvBannerList.add(new BannerMovies(1,"The walking dead", "https://i.pinimg.com/originals/91/0d/67/910d67f4f41a771e3f5f0c50c8f8dd0e.jpg", ""));
        tvBannerList.add(new BannerMovies(2,"Star Wars: Andor", "https://www.starwarsnewsnet.com/wp-content/uploads/2022/08/AndorBanner-800x445.jpg", ""));
        tvBannerList.add(new BannerMovies(3,"X-Men", "https://webneel.net/file/images/11-16/8-xmen-movie-poster-design.preview.jpg", ""));
        tvBannerList.add(new BannerMovies(4,"Matrix", "https://www.whatisthematrix.com/assets/images/desktopbanner.jpg", ""));
        tvBannerList.add(new BannerMovies(5,"The Hobbit", "https://alishahussain27.files.wordpress.com/2014/11/the-hobbit-the-desolation-of-smaug-2013-movie-banner-poster.jpg", ""));

        moviesBannerList = new ArrayList<>();
        moviesBannerList.add(new BannerMovies(1,"The walking dead", "https://i.pinimg.com/originals/91/0d/67/910d67f4f41a771e3f5f0c50c8f8dd0e.jpg", ""));
        moviesBannerList.add(new BannerMovies(2,"Star Wars: Andor", "https://www.starwarsnewsnet.com/wp-content/uploads/2022/08/AndorBanner-800x445.jpg", ""));
        moviesBannerList.add(new BannerMovies(3,"X-Men", "https://webneel.net/file/images/11-16/8-xmen-movie-poster-design.preview.jpg", ""));
        moviesBannerList.add(new BannerMovies(4,"Matrix", "https://www.whatisthematrix.com/assets/images/desktopbanner.jpg", ""));
        moviesBannerList.add(new BannerMovies(5,"The Hobbit", "https://alishahussain27.files.wordpress.com/2014/11/the-hobbit-the-desolation-of-smaug-2013-movie-banner-poster.jpg", ""));

        kidsBannerList = new ArrayList<>();
        kidsBannerList.add(new BannerMovies(1,"The walking dead", "https://i.pinimg.com/originals/91/0d/67/910d67f4f41a771e3f5f0c50c8f8dd0e.jpg", ""));
        kidsBannerList.add(new BannerMovies(2,"Star Wars: Andor", "https://www.starwarsnewsnet.com/wp-content/uploads/2022/08/AndorBanner-800x445.jpg", ""));
        kidsBannerList.add(new BannerMovies(3,"X-Men", "https://webneel.net/file/images/11-16/8-xmen-movie-poster-design.preview.jpg", ""));
        kidsBannerList.add(new BannerMovies(4,"Matrix", "https://www.whatisthematrix.com/assets/images/desktopbanner.jpg", ""));
        kidsBannerList.add(new BannerMovies(5,"The Hobbit", "https://alishahussain27.files.wordpress.com/2014/11/the-hobbit-the-desolation-of-smaug-2013-movie-banner-poster.jpg", ""));


        //Tab por defecto
        setBannerMoviesPagesAdapter(homeBannerList);

        //Cambiamos el Tap
        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                case 1:
                    setBannerMoviesPagesAdapter(tvBannerList);
                    return;
                case 2:
                    setBannerMoviesPagesAdapter(moviesBannerList);
                    return;
                case 3:
                    setBannerMoviesPagesAdapter(kidsBannerList);
                    return;
                default:
                    setBannerMoviesPagesAdapter(homeBannerList);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        List<CategoryItem> homeCatListItem1 = new ArrayList<>();
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(2,"","",""));
        homeCatListItem1.add(new CategoryItem(3,"","",""));
        homeCatListItem1.add(new CategoryItem(4,"","",""));
        homeCatListItem1.add(new CategoryItem(5,"","",""));

        allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory(1, "Categoria1", homeCatListItem1));
        allCategoryList.add(new AllCategory(2, "Categoria2",homeCatListItem1));
        allCategoryList.add(new AllCategory(3, "Categoria3",homeCatListItem1));

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
                        Double rating = Object.getJSONObject("rating").isNull("rating") ? 0.0 : Object.getJSONObject("rating").getDouble("average");
                        String imageOriginal = Object.getJSONObject("image").getString("original");
                        String imageMedium= Object.getJSONObject("image").getString("medium");
                        AllData allData = new AllData(id,name,language,status,premiered,ended,summary,genres,rating,imageOriginal,imageMedium);
                        AllDataList.add(allData);
                        Log.i("Data", String.valueOf(imageMedium));
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
