package com.example.android.bookclublatest.Authentication.SignUp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.android.bookclublatest.Base.BasePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class SignUpPresenter<V extends SignUpContract.View> extends BasePresenter<V> implements SignUpContract.Presenter<V> {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    SignUpModel signUpModel;
    private static final String TAG = "SignUpPresenter";

    @Override
    public void CreateAccount(final String name, final String email, final String admissionnumber, final String phonenumber, final String password, final String image_url) {

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference(); // root node.

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    signUpModel = new SignUpModel(name, email, admissionnumber, phonenumber, "Student", image_url);

                    String node=email.replace('.',',');
                    databaseReference.child("users").child(node).setValue(signUpModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                                Log.e("node", "Uploaded");
                            else
                                Log.e("node", task.getException().getMessage());
                        }
                    });

                    getMvpView().showSignUpResult();
                } else {
                    getMvpView().erroronLoading(task.getException().getMessage());
                }
            }
        });
    }
}
