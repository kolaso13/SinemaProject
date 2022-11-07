package com.example.sinemaproject.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllData {
    Integer id;
    Double rating;
    String name, language, status, premiered, ended, summary,imageOriginal,imageMedium;
    JSONArray genres;

    public AllData(Integer id, String name, String language, String status, String premiered, String ended, String summary, JSONArray genres, Double rating, String imageOriginal, String imageMedium) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.status = status;
        this.premiered = premiered;
        this.ended = ended;
        this.summary = summary;
        this.genres = genres;
        this.rating = rating;
        this.imageOriginal = imageOriginal;
        this.imageMedium = imageMedium;
    }

    public String getImageOriginal() {
        return imageOriginal;
    }

    public void setImageOriginal(String imageOriginal) {
        this.imageOriginal = imageOriginal;
    }

    public String getImageMedium() {
        return imageMedium;
    }

    public void setImageMedium(String imageMedium) {
        this.imageMedium = imageMedium;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public JSONArray getGenres() {
        return genres;
    }

    public void setGenres(JSONArray genres) {
        this.genres = genres;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

}
