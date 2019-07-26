package com.example.android.bookclublatest.Authentication.SignUp;

import android.net.Uri;

import com.example.android.bookclublatest.Base.MvpContract;

public class SignUpContract {


    public interface View extends MvpContract.View{

        void showSignUpResult();

        void erroronLoading(String error);

        void showToast(String url);
    }

    public interface Presenter<V extends View>
    {
        void CreateAccount(String name, String email, String admissionnumber, String phonenumber, String password,String url);

    }
}
