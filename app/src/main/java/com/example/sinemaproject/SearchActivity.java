package com.example.sinemaproject;

import static com.example.sinemaproject.PreLoadActivity.AllDataList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.sinemaproject.adapter.ItemRecyclerAdapter;
import com.example.sinemaproject.adapter.SearchRecyclerAdapter;
import com.example.sinemaproject.model.AllData;
import com.example.sinemaproject.model.CategoryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    List<CategoryItem> search_movies;
    private SearchView svSearch;
    private RecyclerView rvLista;

    SearchRecyclerAdapter searchRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        svSearch = findViewById(R.id.svSearch);
        rvLista = findViewById(R.id.rvLista);

        search_movies = new ArrayList<>();
        for (AllData item:AllDataList) {
            search_movies.add(new CategoryItem(item.getId(), item.getName(), item.getImageOriginal(), item.getLanguage(), item.getStatus(), item.getPremiered(), item.getEnded(), item.getSummary(), item.getGenres(), item.getRating()));
        }
        searchRecyclerAdapter = new SearchRecyclerAdapter(search_movies, this);
        RecyclerView recyclerView = findViewById(R.id.rvLista);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchRecyclerAdapter);

        svSearch.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        searchRecyclerAdapter.filter(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        searchRecyclerAdapter.filter(s);
        return false;
    }
}