package com.example.android.bookclublatest.Authentication.SignUp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPresenter<V extends SignUpContract.View> implements SignUpContract.Presenter<V> {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
     SignUpContract.View view;
    @Override
    public void CreateAccount(String name, String email, String admissionnumber, String phonenumber,String password) {

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();


        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    view.showSignUpResult();

                } else
                {
                    view.erroronLoading(task.getException().getMessage());
                }

            }
        });




     SignUpModel signUpModel=new SignUpModel(name,email,admissionnumber,phonenumber,"Student");
        databaseReference.child(email).setValue(signUpModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    Log.e("node","Uploaded");
                else
                    Log.e("node",task.getException().getMessage());
            }
        });













    }
}
