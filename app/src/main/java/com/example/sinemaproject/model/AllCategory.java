package com.example.sinemaproject.model;

import java.util.List;

public class AllCategory {
    String categoryTitle;
    Integer cateoryId;
    private List<CategoryItem> categoryItemList = null;

    public AllCategory(Integer cateoryId, String categoryTitle,  List<CategoryItem> categoryItemList) {
        this.categoryTitle = categoryTitle;
        this.cateoryId = cateoryId;
        this.categoryItemList = categoryItemList;
    }



    public String getCategoryTitle() {
        return categoryTitle;
    }


    public List<CategoryItem> getCategoryItemList() {
        return categoryItemList;
    }

    public void setCategoryItemList(List<CategoryItem> categoryItemList) {
        this.categoryItemList = categoryItemList;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Integer getCateoryId() {
        return cateoryId;
    }

    public void setCateoryId(Integer cateoryId) {
        this.cateoryId = cateoryId;
    }
}
