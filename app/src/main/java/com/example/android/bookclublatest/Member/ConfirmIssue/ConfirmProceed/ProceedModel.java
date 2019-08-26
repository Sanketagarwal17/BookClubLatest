package com.example.android.bookclublatest.Member.ConfirmIssue.ConfirmProceed;

public class ProceedModel
{
    String bookname,isbn,issue_date,return_date,status,ism,book_returned_on,url;

    public ProceedModel(String bookname, String isbn, String issue_date, String return_date, String status, String ism, String book_returned_on, String url) {
        this.bookname = bookname;
        this.isbn = isbn;
        this.issue_date = issue_date;
        this.return_date = return_date;
        this.status = status;
        this.ism = ism;
        this.book_returned_on = book_returned_on;
        this.url = url;
    }

    public String getBook_returned_on() {
        return book_returned_on;
    }

    public void setBook_returned_on(String book_returned_on) {
        this.book_returned_on = book_returned_on;
    }

    public String getIsm() {
        return ism;
    }

    public String getBookname() {
        return bookname;
    }

    public String getIsbn() {
        return isbn;
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

    public String getUrl() {
        return url;
    }
}
