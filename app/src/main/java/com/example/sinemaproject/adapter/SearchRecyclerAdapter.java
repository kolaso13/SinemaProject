package com.example.sinemaproject.adapter;

import static com.example.sinemaproject.MainActivity.FavoriteMovies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sinemaproject.MovieDetails;
import com.example.sinemaproject.R;
import com.example.sinemaproject.model.CategoryItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder> {
    private List<CategoryItem> searchList;
    private List<CategoryItem> original_searchList;
    private LayoutInflater minflater;
    private Context context;

    public SearchRecyclerAdapter(List<CategoryItem> searchList, Context context) {
        this.minflater = LayoutInflater.from(context);
        this.searchList = searchList;
        this.context = context;
        this.original_searchList= new ArrayList<>();
        original_searchList.addAll(searchList);
    }


    @NonNull
    @Override
    public SearchRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = minflater.inflate(R.layout.search_item, null);
        return new SearchRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(context).load(searchList.get(position).getImageUrl()).into(holder.image);
        holder.title.setText(searchList.get(position).getMovieName());
        holder.rating.setText(searchList.get(position).getRating());
        holder.summary.setText(searchList.get(position).getSummary().replaceAll("<[^>]*>", ""));
        holder.search_genres.setText(Arrays.toString(searchList.get(position).getGenres()).replaceAll("\\[|\\]", ""));

        holder.search_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean fav = FavoriteMovies.contains(searchList.get(position).getMovieName());
                Intent i = new Intent(context, MovieDetails.class);
                i.putExtra("movieId", searchList.get(position).getId());
                i.putExtra("movieName", searchList.get(position).getMovieName());
                i.putExtra("movieImageUrl",searchList.get(position).getImageUrl());
                i.putExtra("movieLanguage", searchList.get(position).getLanguage());
                i.putExtra("movieStatus", searchList.get(position).getStatus());
                i.putExtra("moviePremiered", searchList.get(position).getPremiered());
                i.putExtra("movieEnded", searchList.get(position).getEnded());
                i.putExtra("movieSummary", searchList.get(position).getSummary());
                i.putExtra("movieGenres", searchList.get(position).getGenres());
                i.putExtra("movieRating", searchList.get(position).getRating());
                i.putExtra("fav", fav);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public void filter(String strSearch){
        Log.i("Text", strSearch);
        if(strSearch.length() == 0){
            searchList.clear();
            searchList.addAll(original_searchList);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<CategoryItem> collect = searchList.stream()
                        .filter(item -> item.getMovieName().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                searchList.clear();
                searchList.addAll(collect);
                notifyDataSetChanged();
            }
            else{
                searchList.clear();
                for (CategoryItem item:searchList) {
                    if(item.getMovieName().toLowerCase().contains(strSearch)){
                        searchList.add(item);
                        notifyDataSetChanged();
                    }
                }
            }
        }

    }

    public void  setItem(List<CategoryItem> items){searchList = items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout search_layout;
        ImageView image;
        TextView title, rating, summary, search_genres;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            search_layout = itemView.findViewById(R.id.search_layout);
            image = itemView.findViewById(R.id.search_Image);
            title = itemView.findViewById(R.id.search_title);
            rating = itemView.findViewById(R.id.search_rating);
            summary = itemView.findViewById(R.id.summary);
            search_genres = itemView.findViewById(R.id.search_genres);
        }

    }
}
