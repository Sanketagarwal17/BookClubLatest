package com.example.android.bookclublatest.Member.ConfirmReturn;

import java.io.Serializable;

public class ConfirmModel implements Serializable
{
    String email,bookname,isbn,ismcode,issue_date,return_date,status,url;

    public ConfirmModel(String email, String bookname, String isbn, String ismcode, String issue_date, String return_date, String status, String url) {
        this.email = email;
        this.bookname = bookname;
        this.isbn = isbn;
        this.ismcode = ismcode;
        this.issue_date = issue_date;
        this.return_date = return_date;
        this.status = status;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getEmail() {
        return email;
    }

    public String getBookname() {
        return bookname;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getIsmcode() {
        return ismcode;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public String getStatus() {
        return status;
    }
}
