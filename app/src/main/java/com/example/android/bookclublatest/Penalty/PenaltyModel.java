package com.example.android.bookclublatest.Penalty;

public class PenaltyModel{
    public  String issue_date,return_date,isbn,bookname,returned_on;

    public PenaltyModel(String issue_date, String return_date, String isbn, String bookname, String returned_on) {
        this.issue_date = issue_date;
        this.return_date = return_date;
        this.isbn = isbn;
        this.bookname = bookname;
        this.returned_on = returned_on;
    }

    public String getReturned_on() {
        return returned_on;
    }

    public void setReturned_on(String returned_on) {
        this.returned_on = returned_on;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
}
