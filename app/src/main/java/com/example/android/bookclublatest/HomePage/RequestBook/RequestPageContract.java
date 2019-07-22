package com.example.android.bookclublatest.HomePage.RequestBook;

public class RequestPageContract
{
    public interface View
    {
        void showToast(String message);
    }


    public interface Presenter
    {
        void submit(String book,String author, String publication,String info);
    }
}
