package com.example.android.bookclublatest.HomePage.RequestBook;

public class RequestModel
{
    String book,author,publ,info,email,status,requested_date, approval_date, id;

    public RequestModel() {
    }

    public RequestModel(String book, String author, String publ, String info, String email, String status, String requested_date, String approval_date,String id) {
        this.book = book;
        this.author = author;
        this.publ = publ;
        this.info = info;
        this.email = email;
        this.status = status;
        this.requested_date = requested_date;
        this.approval_date = approval_date;
        this.id = id;
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

    public String getRequested_date() {
        return requested_date;
    }

    public String getApproval_date() {
        return approval_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
