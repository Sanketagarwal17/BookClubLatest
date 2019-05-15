package com.example.android.bookclublatest.Authentication.Login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter<V extends LoginContract.View>
        implements LoginContract.Presenter<V> {


    LoginContract.View view;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    public void doLogin(String email, String password) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                               view.showloginResult();
                        } else {
                            view.errorOnLoading(task.getException().getMessage());
                        }
                }
                });

    }
}






