package com.example.android.bookclublatest.Authentication.Login;


import com.example.android.bookclublatest.Base.MvpContract;

public class LoginContract {

    public interface View extends MvpContract.View{
        void showloginResult();

        void errorOnLoading(String error);



    }

    public interface Presenter<V extends View> //extends MvpContract.Presenter<V>//
    {

  public void doLogin(String email, String password);
    }
}
