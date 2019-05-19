package com.example.android.bookclublatest.HomePage.RequestBook;

public class RequestModel
{
    String book,author,publ;

    public RequestModel(String book, String author, String publ) {
        this.book = book;
        this.author = author;
        this.publ = publ;
    }

    public String getBook() {
        return book;
    }

    public String getAuthor() {
        return author;
    }

    public String getPubl() {
        return publ;
    }
}
