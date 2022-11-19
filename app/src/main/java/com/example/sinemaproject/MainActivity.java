package com.example.sinemaproject;

import static com.example.sinemaproject.PreLoadActivity.AllDataList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    BannerMoviesPagesAdapter bannerMoviesPagesAdapter;
    TabLayout indicatorTab;
    ImageView search;
    ViewPager bannerMoviesViewPager;
    List<BannerMovies> homeBannerList;

    public static List<Integer> FavoriteMovies;
    TextView appTitle;

    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;
    List<AllCategory> allCategoryList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicatorTab = findViewById(R.id.tab_indicator);
        search = findViewById(R.id.search);
        appTitle = findViewById(R.id.applicationTitle);

        // llamada al metodo para cargar las preferencias
        loadData();

        //Numeros random para el carrusel
        Random r = new Random();
        int randomN1 = r.nextInt(AllDataList.size());
        int randomN2 = r.nextInt(AllDataList.size());
        int randomN3 = r.nextInt(AllDataList.size());
        int randomN4 = r.nextInt(AllDataList.size());

        //Añadimos series random para el carrusel
        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerMovies(AllDataList.get(randomN1).getId(), AllDataList.get(randomN1).getName(), AllDataList.get(randomN1).getImageOriginal(), AllDataList.get(randomN1).getLanguage(), AllDataList.get(randomN1).getStatus(), AllDataList.get(randomN1).getPremiered(), AllDataList.get(randomN1).getEnded(), AllDataList.get(randomN1).getSummary(), AllDataList.get(randomN1).getGenres()));
        homeBannerList.add(new BannerMovies(AllDataList.get(randomN2).getId(), AllDataList.get(randomN2).getName(), AllDataList.get(randomN2).getImageOriginal(), AllDataList.get(randomN2).getLanguage(), AllDataList.get(randomN2).getStatus(), AllDataList.get(randomN2).getPremiered(), AllDataList.get(randomN2).getEnded(), AllDataList.get(randomN2).getSummary(), AllDataList.get(randomN2).getGenres()));
        homeBannerList.add(new BannerMovies(AllDataList.get(randomN3).getId(), AllDataList.get(randomN3).getName(), AllDataList.get(randomN3).getImageOriginal(), AllDataList.get(randomN3).getLanguage(), AllDataList.get(randomN3).getStatus(), AllDataList.get(randomN3).getPremiered(), AllDataList.get(randomN3).getEnded(), AllDataList.get(randomN3).getSummary(), AllDataList.get(randomN3).getGenres()));
        homeBannerList.add(new BannerMovies(AllDataList.get(randomN4).getId(), AllDataList.get(randomN4).getName(), AllDataList.get(randomN4).getImageOriginal(), AllDataList.get(randomN4).getLanguage(), AllDataList.get(randomN4).getStatus(), AllDataList.get(randomN4).getPremiered(), AllDataList.get(randomN4).getEnded(), AllDataList.get(randomN4).getSummary(), AllDataList.get(randomN4).getGenres()));

        //Añadimos las series al adaptador
        setBannerMoviesPagesAdapter(homeBannerList);


        //Creamos las listas con las diferentes categorias
        List<CategoryItem> FavMoviesList = null;
        List<CategoryItem> DramaCatListItem = new ArrayList<>();
        List<CategoryItem> ComedyCatListItem = new ArrayList<>();
        List<CategoryItem> ActionCatListItem = new ArrayList<>();
        List<CategoryItem> AventureCatListItem = new ArrayList<>();
        List<CategoryItem> CrimeCatListItem = new ArrayList<>();
        List<CategoryItem> ScienceFictionCatListItem = new ArrayList<>();
        Log.i("Size", String.valueOf(AllDataList.size()));


        //Filtramos en las categorias todos los datos recogidos de la API
        for (AllData item:AllDataList) {
            for(int i=0;i<item.getGenres().length;i++){
                Log.i("Size", String.valueOf(item.getGenres()[i]));
                if(item.getGenres()[i].equalsIgnoreCase("Drama")){
                    DramaCatListItem.add(new CategoryItem(item.getId(), item.getName(), item.getImageOriginal(), item.getLanguage(), item.getStatus(), item.getPremiered(), item.getEnded(), item.getSummary(), item.getGenres()));
                }
                if(item.getGenres()[i].equalsIgnoreCase("Comedy")){
                    ComedyCatListItem.add(new CategoryItem(item.getId(), item.getName(), item.getImageOriginal(), item.getLanguage(), item.getStatus(), item.getPremiered(), item.getEnded(), item.getSummary(), item.getGenres()));
                }
                if(item.getGenres()[i].equalsIgnoreCase("Action")){
                    ActionCatListItem.add(new CategoryItem(item.getId(), item.getName(), item.getImageOriginal(), item.getLanguage(), item.getStatus(), item.getPremiered(), item.getEnded(), item.getSummary(), item.getGenres()));
                }
                if(item.getGenres()[i].equalsIgnoreCase("Adventure")){
                    AventureCatListItem.add(new CategoryItem(item.getId(), item.getName(), item.getImageOriginal(), item.getLanguage(), item.getStatus(), item.getPremiered(), item.getEnded(), item.getSummary(), item.getGenres()));
                }
                if(item.getGenres()[i].equalsIgnoreCase("Crime")){
                    CrimeCatListItem.add(new CategoryItem(item.getId(), item.getName(), item.getImageOriginal(), item.getLanguage(), item.getStatus(), item.getPremiered(), item.getEnded(), item.getSummary(), item.getGenres()));
                }
                if(item.getGenres()[i].equalsIgnoreCase("Science-Fiction")){
                    ScienceFictionCatListItem.add(new CategoryItem(item.getId(), item.getName(), item.getImageOriginal(), item.getLanguage(), item.getStatus(), item.getPremiered(), item.getEnded(), item.getSummary(), item.getGenres()));
                }
            }
        }
        if(!FavoriteMovies.isEmpty()) {
            FavMoviesList = new ArrayList<>();

            for (AllData item : AllDataList) {
                for (int i = 0; i < FavoriteMovies.size(); i++) {
                    if (item.getId() == FavoriteMovies.get(i)){
                        FavMoviesList.add(new CategoryItem(item.getId(), item.getName(), item.getImageOriginal(), item.getLanguage(), item.getStatus(), item.getPremiered(), item.getEnded(), item.getSummary(), item.getGenres()));
                    }
                    Log.i("Fav", String.valueOf(AllDataList.get(15).getId()));
                }
            }
        }
        //Añadimos las categorias para mostrarlas en el main con sus peliculas
        allCategoryList = new ArrayList<>();
        if(!FavoriteMovies.isEmpty()){
            allCategoryList.add(new AllCategory(1, "Favoritas", FavMoviesList));
        }
        allCategoryList.add(new AllCategory(2, "Drama", DramaCatListItem));
        allCategoryList.add(new AllCategory(3, "Comedia", ComedyCatListItem));
        allCategoryList.add(new AllCategory(4, "Acción",ActionCatListItem));
        allCategoryList.add(new AllCategory(5, "Aventura",AventureCatListItem));
        allCategoryList.add(new AllCategory(6, "Suspense",CrimeCatListItem));
        allCategoryList.add(new AllCategory(7, "Ciencia-Ficción",ScienceFictionCatListItem));

        //Pasamos el array de categorias al recycler
        setMainRecycler(allCategoryList);

        //Boton para buscar
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        //Boton para guardar preferencias
        appTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
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

    //Guardamos los favoritos en Shared Pref
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(FavoriteMovies);

        editor.putString("courses", json);
        editor.apply();
//        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
    }

    //Cargamos los favoritos de Shared Pref
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("courses", null);
        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        FavoriteMovies = gson.fromJson(json, type);
        if (FavoriteMovies == null) {
            FavoriteMovies = new ArrayList<>();
        }
    }
}
