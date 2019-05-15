package com.example.android.bookclublatest.Authentication.Login;

import android.support.annotation.NonNull;
import com.example.android.bookclublatest.Base.BasePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter<V extends LoginContract.View> extends BasePresenter<V>
        implements LoginContract.Presenter<V> {

   // LoginContract.View view = getMvpView();
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    public void doLogin(String email, String password) {
        firebaseAuth = FirebaseAuth.getInstance();


        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                               getMvpView().showloginResult();
                        } else {
                            getMvpView().errorOnLoading(task.getException().getMessage());
                        }
                }
                });
        firebaseUser=firebaseAuth.getCurrentUser();
    }
}






