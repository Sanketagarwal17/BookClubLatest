package com.example.android.bookclublatest.Authentication.Verification;

import com.example.android.bookclublatest.Authentication.SignUp.SignUpContract;
import com.example.android.bookclublatest.Base.MvpContract;

public class VerificationContract
{
    public interface View extends MvpContract.View
    {
        void showEmail(String string);
        void showPhone(String string);
        void showToast(String string);
        void disableEmailVerification();
    }
    public interface Presenter<V extends View>
    {
        void fillEmail();
        void getEmail();
        void getPhone();
        void checkEmailVerified();
    }
}