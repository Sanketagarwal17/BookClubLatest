package com.example.android.bookclublatest.Authentication.SignUp;

import com.example.android.bookclublatest.Base.MvpContract;

public class SignUpContract {


    public interface View extends MvpContract.View{

        void showSignUpResult();

        void erroronLoading(String error);

    }

    public interface Presenter<V extends View>
    {
        void CreateAccount(String name, String email, String admissionnumber,String phonenumber,String password);

    }
}
