package com.example.android.bookclublatest.Member.ConfirmIssue;

public class ConfirmIssueModel
{
    String email,isbn,name,date;

    public ConfirmIssueModel(String email, String isbn, String name, String date) {
        this.email = email;
        this.isbn = isbn;
        this.name = name;
        this.date = date;
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
}
