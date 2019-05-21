package com.example.android.bookclublatest.HomePage.RequestBook;

import android.support.annotation.NonNull;

import com.example.android.bookclublatest.Authentication.Login.LoginContract;
import com.example.android.bookclublatest.Base.BasePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestPagePresenter <V extends RequestPageContract.View> extends BasePresenter<V>
        implements RequestPageContract.Presenter<V>
{
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference().child("Request Book");
    @Override
    public void submit(String book, String author, String publication) {
         String reqKey = databaseReference.push().getKey();
        databaseReference.child(reqKey).setValue(new RequestModel(book,author,publication))// generating a random key for each book request.
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            getMvpView().showToast("Submitted");
                        else
                            getMvpView().showToast(task.getException().getMessage());
                    }
                });
    }
}
