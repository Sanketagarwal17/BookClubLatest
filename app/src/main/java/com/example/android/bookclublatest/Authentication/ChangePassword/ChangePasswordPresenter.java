package com.example.android.bookclublatest.Authentication.ChangePassword;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.android.bookclublatest.Authentication.ChangePassword.ChangePasswordContract;
import com.example.android.bookclublatest.Base.BasePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordPresenter<V extends ChangePasswordContract.View> extends BasePresenter<V>
        implements ChangePasswordContract.Presenter<V> {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    public void changePassword(String email, String password, final String newpassword) {
        AuthCredential credential = EmailAuthProvider.getCredential(email,password);

        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    user.updatePassword(newpassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(!task.isSuccessful()){
                                getMvpView().errorOnLoading(task.getException().getMessage().toString());
                            }else {
                                getMvpView().errorOnLoading("Password Change Successfully");
                                getMvpView().close();


                            }
                        }
                    });
                }else {
                    getMvpView().errorOnLoading("Authentication Failed");

                }
            }
        });
    }
}
