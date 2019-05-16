package com.example.android.bookclublatest.Authentication.Verification;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.android.bookclublatest.Authentication.SignUp.SignUpContract;
import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.Base.BasePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerificationPresenter<V extends VerificationContract.View> extends BasePresenter<V> implements VerificationContract.Presenter<V>
{
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

    @Override
    public void fillEmail() {
        String email=firebaseUser.getEmail();
        getMvpView().showEmail(email);
    }

    @Override
    public void getEmail() 
    {
        firebaseUser.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            getMvpView().showToast("Email Verification Link sent");
                        else
                            getMvpView().showToast(task.getException().getMessage());
                    }
                });
    }

    @Override
    public void checkEmailVerified()
    {
        firebaseUser.reload();
        Boolean check=firebaseUser.isEmailVerified();
        if(check)
        {
            getMvpView().disableEmailVerification();
        }
    }

    @Override
    public void getPhone() {

    }

}
