package com.example.marry.newsapp;

public class Article {
    //variable to hold the data about the Section
    private String Section;
    //variable to hold the data about the Title
    private String Title;
    //variable to hold the data about the Date
    private String Date;
    //variable to hold the data about the Url
    private String URL;
    //variable to hold the data about the author
    private  String Author;


    //the constructor of the class
    public Article(String section, String title, String date, String url, String author) {
        Section = section;
        Title = title;
        Date = date;
        URL = url;
        Author = author;
    }

    //method to get the info about the Section
    public String getSection() {
        return Section;
    }

    //method to get the info about the Title
    public String getTitle() {
        return Title;
    }

    //method to get the info about the Date
    public String getDate() {
        return Date;
    }

    //method to get the info about the URL
    public String getURL() {
        return URL;
    }

    //method to get the info about the author
    public String getAuthor() {
        return Author;
    }
}
