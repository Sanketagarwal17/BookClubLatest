package com.example.android.bookclublatest.HomePage.BrowseBooks;

public class BrowseModel
{
    String name,author,publisher,hard,soft;

    public BrowseModel(String name, String author, String publisher, String hard, String soft) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.hard = hard;
        this.soft = soft;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getHard() {
        return hard;
    }

    public String getSoft() {
        return soft;
    }
}
