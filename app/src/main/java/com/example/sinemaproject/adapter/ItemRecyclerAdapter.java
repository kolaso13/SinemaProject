package com.example.sinemaproject.adapter;

import static com.example.sinemaproject.MainActivity.FavoriteMovies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sinemaproject.MovieDetails;
import com.example.sinemaproject.R;
import com.example.sinemaproject.model.CategoryItem;

import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {

    Context context;
    List<CategoryItem> categoryItemList;

    public ItemRecyclerAdapter(Context context, List<CategoryItem> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.cat_recycler_row_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(context).load(categoryItemList.get(position).getImageUrl()).into(holder.itemImage);

        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean fav = FavoriteMovies.contains(categoryItemList.get(position).getId());
                Intent i = new Intent(context, MovieDetails.class);
                i.putExtra("movieId", categoryItemList.get(position).getId());
                i.putExtra("movieName", categoryItemList.get(position).getMovieName());
                i.putExtra("movieImageUrl",categoryItemList.get(position).getImageUrl());
                i.putExtra("movieLanguage", categoryItemList.get(position).getLanguage());
                i.putExtra("movieStatus", categoryItemList.get(position).getStatus());
                i.putExtra("moviePremiered", categoryItemList.get(position).getPremiered());
                i.putExtra("movieEnded", categoryItemList.get(position).getEnded());
                i.putExtra("movieSummary", categoryItemList.get(position).getSummary());
                i.putExtra("movieGenres", categoryItemList.get(position).getGenres());
                i.putExtra("fav", fav);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;

        public ItemViewHolder(@NonNull View itemView){
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
        }
    }
}
