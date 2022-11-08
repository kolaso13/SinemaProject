package com.example.sinemaproject.model;

public class CategoryItem {

    Integer id;
    String movieName;
    String imageUrl;


    public CategoryItem(Integer id, String movieName, String imageUrl) {
        this.id = id;
        this.movieName = movieName;
        this.imageUrl = imageUrl;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
