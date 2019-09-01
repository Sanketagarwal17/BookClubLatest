package com.example.android.bookclublatest.Member.ConfirmIssue;

import java.util.Comparator;

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

    public  static  final Comparator<ConfirmIssueModel> By_Name=new Comparator<ConfirmIssueModel>() {
    @Override
    public int compare(ConfirmIssueModel o1, ConfirmIssueModel o2) {
        return o1.getName().compareTo(o2.getName());

      }
    };
    public  static  final Comparator<ConfirmIssueModel> By_Time=new Comparator<ConfirmIssueModel>() {
        @Override
        public int compare(ConfirmIssueModel o1, ConfirmIssueModel o2) {
            return o1.getDate().compareTo(o2.getDate());

        }
    };
}
