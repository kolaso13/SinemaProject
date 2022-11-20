package com.example.sinemaproject.model;

public class BannerMovies {
    Integer id;
    String movieName, imageUrl, language, status, premiered, ended, summary, rating;
    String[] genres;




    public BannerMovies(Integer id, String movieName, String imageUrl, String language, String status, String premiered, String ended, String summary, String[] genres, String rating) {
        this.id = id;
        this.movieName = movieName;
        this.imageUrl = imageUrl;
        this.language = language;
        this.status = status;
        this.premiered = premiered;
        this.ended = ended;
        this.summary = summary;
        this.genres = genres;
        this.rating = rating;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPremiered() {
        return premiered;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public String getEnded() {
        return ended;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getRating() {return rating;}

    public void setRating(String rating) {this.rating = rating;}
}
