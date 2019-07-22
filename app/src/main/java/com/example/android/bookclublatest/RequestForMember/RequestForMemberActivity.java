package com.example.android.bookclublatest.RequestForMember;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.bookclublatest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestForMemberActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    Button sendrequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_for_member);
        sendrequest=findViewById(R.id.sendrequestformember);

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();


        final String email1=firebaseUser.getEmail();
        final String email=email1.replace('.',',');


        databaseReference = FirebaseDatabase.getInstance().getReference(); // root node.


    sendrequest.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

             databaseReference.child("requstsformember").child(email).setValue(email1).addOnCompleteListener(new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull Task<Void> task) {
                     if(task.isSuccessful())
                     {
                         Toast.makeText(RequestForMemberActivity.this,"Request Send",Toast.LENGTH_LONG).show();
                     }
                     else
                     {
                         Toast.makeText(RequestForMemberActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                     }
                 }
             });




        }
    });








    }










}
