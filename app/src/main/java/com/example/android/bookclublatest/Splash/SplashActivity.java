package com.example.android.bookclublatest.Splash;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity
{
    android.os.Handler Handler = new Handler();
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference("Status");
    SharedPref sharedPref;
    String email;
    String accesslevel="";
    String versionName="";
    String database_versionname="";
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    ValueEventListener listener;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Version");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        sharedPref=new SharedPref(this);

        try
        {
            versionName = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }

        listener=reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    database_versionname = ds.getValue().toString();

                    if(versionName.equals(database_versionname)) {

                        if (!sharedPref.getEmail().isEmpty()) {
                            Handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    enterapp();
                                }
                            }, 300);
                        } else {
                            Handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                    progressBar.setVisibility(View.GONE);
                                    startActivity(new Intent(SplashActivity.this, HomePageActivity.class));
                                }
                            }, 1500);
                        }
                    }
                    else{
                        progressBar.setVisibility(View.GONE);
                        showUpdate();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    private void showUpdate()
    {

        final AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.setTitle("UPDATE");
        alertDialog.setMessage("Please update the app first !");
        alertDialog.setCancelable(false);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Update Now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.rishabh.sorpluserend"));
                startActivity(intent);
                alertDialog.dismiss();
                finish();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                finish();
            }
        });
        alertDialog.show();
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
                        progressBar.setVisibility(View.GONE);
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


    @Override
    protected void onStop() {
        super.onStop();
        if (reference != null && listener != null) {
            reference.removeEventListener(listener);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (reference != null && listener != null) {
            reference.removeEventListener(listener);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (reference != null && listener != null) {
            reference.removeEventListener(listener);
        }
    }
}
