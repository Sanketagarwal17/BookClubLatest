package com.example.android.bookclublatest.Authentication.Login;

import android.support.annotation.NonNull;

import com.example.android.bookclublatest.Authentication.SignUp.SignUpModel;
import com.example.android.bookclublatest.Base.BasePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPresenter<V extends LoginContract.View> extends BasePresenter<V>
        implements LoginContract.Presenter<V> {

   // LoginContract.View view = getMvpView();
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    public void doLogin(final String email, String password) {
        firebaseAuth = FirebaseAuth.getInstance();


        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            getMvpView().showloginResult();
                        }
                        else
                        {
                            getMvpView().errorOnLoading(task.getException().getMessage());
                        }
                }
                });
        firebaseUser=firebaseAuth.getCurrentUser();
    }
}






