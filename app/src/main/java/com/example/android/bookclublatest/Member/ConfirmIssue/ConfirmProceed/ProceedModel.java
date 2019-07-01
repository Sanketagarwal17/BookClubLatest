package com.example.android.bookclublatest.Member.ConfirmIssue.ConfirmProceed;

public class ProceedModel
{
    String bookname,isbn,issue_date,return_date,status;

    public ProceedModel(String bookname, String isbn, String issue_date, String return_date, String status) {
        this.bookname = bookname;
        this.isbn = isbn;
        this.issue_date = issue_date;
        this.return_date = return_date;
        this.status = status;
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
}
