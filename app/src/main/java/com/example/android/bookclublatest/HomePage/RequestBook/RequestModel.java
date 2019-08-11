package com.example.android.bookclublatest.HomePage.RequestBook;

public class RequestModel
{
    String book,author,publ,info,email,status;

    public RequestModel() {
    }

    public RequestModel(String book, String author, String publ, String info, String email, String status) {
        this.book = book;
        this.author = author;
        this.publ = publ;
        this.info = info;
        this.email = email;
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }
}
