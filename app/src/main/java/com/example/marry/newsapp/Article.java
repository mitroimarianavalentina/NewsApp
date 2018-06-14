package com.example.marry.newsapp;

public class Article {
    //variable to hold the data about the Section
    private String mSection;
    //variable to hold the data about the Title
    private String mTitle;
    //variable to hold the data about the Date
    private String mDate;
    //variable to hold the data about the Url
    private String mUrl;

    //the constructor of the class
    public Article(String section, String title, String date, String url) {
        mSection = section;
        mTitle = title;
        mDate = date;
        mUrl = url;
    }

    //method to get the info about the Section
    public String getSection() {
        return mSection;
    }

    //method to get the info about the Title
    public String getTitle() {
        return mTitle;
    }

    //method to get the info about the Date
    public String getDate() {
        return mDate;
    }

    //method to get the info about the URL
    public String getUrl() {
        return mUrl;
    }
}
