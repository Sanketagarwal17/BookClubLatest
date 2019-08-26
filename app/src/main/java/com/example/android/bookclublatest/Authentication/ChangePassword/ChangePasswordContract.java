package com.example.android.bookclublatest.Authentication.ChangePassword;

import com.example.android.bookclublatest.Authentication.Login.LoginContract;
import com.example.android.bookclublatest.Base.MvpContract;

public class ChangePasswordContract {

    public interface View extends MvpContract.View{

        void errorOnLoading(String error);


        void close();
    }

    public interface Presenter<V extends View> //extends MvpContract.Presenter<V>//
    {

        public void changePassword(String email,String password,String newpassword);
    }
}
