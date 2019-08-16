package com.example.android.bookclublatest.Member.ConfirmIssue;

public class ConfirmIssueModel
{
    String email,isbn,name,date,ism,url;

    public ConfirmIssueModel(String email, String isbn, String name, String date,String ism,String url) {
        this.email = email;
        this.isbn = isbn;
        this.name = name;
        this.date = date;
        this.ism = ism;
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getIsm() {
        return ism;
    }

    public String getUrl() {
        return url;
    }
}
