package com.example.android.bookclublatest.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookclublatest.Authentication.Verification.VerificationActivity;
import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.R;
import com.example.android.bookclublatest.SharedPref.SharedPref;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends BaseActivity implements ProfileContract.View
{
    ProfileContract.Presenter<ProfileContract.View> mpresenter;

    @BindView(R.id.profile_name)
    TextView name;
    @BindView(R.id.profile_email)
    TextView email;
    @BindView(R.id.profile_admision)
    TextView admission;
    @BindView(R.id.profile_status)
    TextView status;
    @BindView(R.id.profile_verify_email)
    Button verify;
    @BindView(R.id.profile_mobile)
    TextView mobile;

    SharedPref sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        ButterKnife.bind(this);
        mpresenter = new ProfilePresenter<ProfileContract.View>();
        sharedPref=new SharedPref(this);
        status.setText(sharedPref.getAccessLevel());
        mobile.setText(sharedPref.getMobile());
        admission.setText(sharedPref.getAdmission());
        email.setText(sharedPref.getEmail());
        name.setText(sharedPref.getUsername());

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified())
                    Toast.makeText(ProfileActivity.this, "You are already Email Verified", Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(ProfileActivity.this, VerificationActivity.class));
            }
        });

    }
}
