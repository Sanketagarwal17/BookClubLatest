package com.example.android.bookclublatest.HomePage;

import com.example.android.bookclublatest.Base.MvpContract;

public class HomePageContract {
    public interface View extends MvpContract.View {


    }


    public interface Presenter<V extends View> extends MvpContract.Presenter<V> {

    }
}
