package com.example.marry.newsapp;

public class Article {
    //variable to hold the data about the section
    private String section;
    //variable to hold the data about the title
    private String title;
    //variable to hold the data about the date
    private String date;
    //variable to hold the data about the Url
    private String url;
    //variable to hold the data about the author
    private  String author;


    //the constructor of the class
    public Article(String section, String title, String date, String url, String author) {
        this.section = section;
        this.title = title;
        this.date = date;
        this.url = url;
        this.author = author;
    }

    //method to get the info about the section
    public String getSection() {
        return section;
    }

    //method to get the info about the title
    public String getTitle() {
        return title;
    }

    //method to get the info about the date
    public String getDate() {
        return date;
    }

    //method to get the info about the url
    public String getUrl() {
        return url;
    }

    //method to get the info about the author
    public String getAuthor() {
        return author;
    }
}
