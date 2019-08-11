package com.example.android.bookclublatest.HomePage.RequestBook;

public class RequestPageContract
{
    public interface View
    {
        void showToast(String message);

        void showSuccess(String s);
    }


    public interface Presenter
    {
        void submit(String book,String author, String publication,String info, String email);
    }
}
