package com.example.android.bookclublatest.Authentication.Verification;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.R;
import com.example.android.bookclublatest.SharedPref.SharedPref;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerificationActivity extends BaseActivity implements VerificationContract.View
{
    @BindView(R.id.verify_email)
    Button email_button;

    @BindView(R.id.verify_email_get)
    TextView email_button_get;

    @BindView(R.id.verify_phone)
    Button phone_button;

    @BindView(R.id.verify_phone_get)
    TextView phone_button_get;



    SharedPref sharedPref;
    VerificationContract.Presenter<VerificationContract.View> mpresenter;
    String phonenumber;
    boolean IS_PHONE_VERIFY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        ButterKnife.bind(this);
        sharedPref=new SharedPref(this);
        final String phonenumber=sharedPref.getMobile();

        phone_button_get.setText(phonenumber);

        SharedPreferences prefs = getSharedPreferences("IS_PHONE_VERIFIED", MODE_PRIVATE);
        boolean IS_PHONE_VERIFY = prefs.getBoolean("phone verify",false);


        if(IS_PHONE_VERIFY)
        {  phone_button.setEnabled(false);
            phone_button.setText("Verified");

        }



        mpresenter=new VerificationPresenter<VerificationContract.View>();
        ((VerificationPresenter<VerificationContract.View>)mpresenter).onAttach(this);



        mpresenter.checkEmailVerified();
        mpresenter.fillEmail();


        email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpresenter.getEmail();
            }
        });

        phone_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                startActivity(new Intent(VerificationActivity.this,OtpActivity.class));
                mpresenter.verifyPhone(phonenumber);



                }
        });


    }

    @Override
    public void showEmail(String string)
    {
        email_button_get.setText(string);
    }



    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void disableEmailVerification()
    {
        email_button.setEnabled(false);
        email_button.setText("Verified");
    }

    @Override
    public void disablePhoneVerification() {
        phone_button.setEnabled(false);
        phone_button.setText("Verified");
    }



}
