package com.example.marry.newsapp;

public class Article {
    private String mSection;
    private String mTitle;
    private String mDate;
    private String mUrl;

    public Article(String section, String title, String date, String url) {
        mSection = section;
        mTitle = title;
        mDate = date;
        mUrl = url;
    }

    public String getSection() {
        return mSection;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }
}
