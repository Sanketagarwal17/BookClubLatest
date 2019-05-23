package com.example.android.bookclublatest.HomePage.BrowseBooks;

import com.example.android.bookclublatest.Base.MvpContract;

import java.util.ArrayList;

public class BrowsePageContract
{

    public interface View extends MvpContract.View {

        void showToast(String message);
    }


    public interface Presenter<V extends View> extends MvpContract.Presenter<V> {

    }
}
