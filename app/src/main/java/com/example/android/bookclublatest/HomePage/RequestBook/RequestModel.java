package com.example.android.bookclublatest.HomePage.RequestBook;

public class RequestModel
{
    String book,author,publ,info;

    public RequestModel(String book, String author, String publ, String info) {
        this.book = book;
        this.author = author;
        this.publ = publ;
        this.info = info;
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

    public String getInfo() {
        return info;
    }
}
