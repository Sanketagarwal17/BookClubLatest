package com.example.android.bookclublatest.HomePage.RequestBook;

import com.example.android.bookclublatest.Base.MvpContract;

public class RequestPageContract
{

    public interface View extends MvpContract.View {

        void showToast(String message);
    }


    public interface Presenter<V extends View> extends MvpContract.Presenter<V> {
        void submit(String book,String author, String publication);
    }
}
