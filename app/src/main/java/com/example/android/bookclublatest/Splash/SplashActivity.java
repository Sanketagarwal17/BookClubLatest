package com.example.android.bookclublatest.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.bookclublatest.Authentication.Login.LoginActivity;
import com.example.android.bookclublatest.HomePage.HomePageActivity;
import com.example.android.bookclublatest.R;
import com.example.android.bookclublatest.SharedPref.SharedPref;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity
{
    android.os.Handler Handler = new Handler();
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference("Status");
    SharedPref sharedPref;
    String email;
    String accesslevel="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPref=new SharedPref(this);

        if(!sharedPref.getEmail().isEmpty())
        {
            Handler.postDelayed(new Runnable() {
                @Override
                public void run()
                {
                    //startActivity(new Intent(SplashActivity.this,HomePageActivity.class));
                    enterapp();
                }
            },1500);
        }
        else
        {
            Handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
            },1000);
        }


    }

    private void enterapp()
    {
        email=sharedPref.getEmail();
        email=email.replace('.',',');

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    if(ds.getKey().equals(email))
                    {
                        accesslevel=ds.getValue().toString();
                        sharedPref.setAccessLevel(accesslevel);
                        finish();
                        startActivity(new Intent(SplashActivity.this,HomePageActivity.class));
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
