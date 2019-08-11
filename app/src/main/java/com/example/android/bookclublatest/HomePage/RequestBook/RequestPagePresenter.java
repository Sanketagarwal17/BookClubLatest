package com.example.android.bookclublatest.HomePage.RequestBook;

import android.support.annotation.NonNull;

import com.example.android.bookclublatest.Authentication.Login.LoginContract;
import com.example.android.bookclublatest.Base.BasePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RequestPagePresenter implements RequestPageContract.Presenter
{
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference=firebaseDatabase.getReference().child("Request Book");

    RequestPageContract.View getMvpView;

    public RequestPagePresenter(RequestPageContract.View getMvpView) {
        this.getMvpView = getMvpView;
    }

    @Override
    public void submit(String book, String author, String publication,String info, String email) {
        String reqKey = databaseReference.push().getKey();
        if(publication.isEmpty())
            publication="Not Given";
        if(info.isEmpty())
            info="Not Given";
        databaseReference.child(reqKey).setValue(new RequestModel(book,author,publication,info, email, "Pending"))// generating a random key for each book request.
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            getMvpView.showSuccess("Submitted !");
                        else
                            getMvpView.showToast(task.getException().getMessage());
                    }
                });
    }
}
