package com.example.android.bookclublatest.Authentication.Verification;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.bookclublatest.HomePage.HomePageActivity;
import com.example.android.bookclublatest.R;
import com.example.android.bookclublatest.SharedPref.SharedPref;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtpActivity extends AppCompatActivity {
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;

    FirebaseAuth mauth = FirebaseAuth.getInstance();
    @BindView(R.id.otp)
    EditText otp;

    @BindView(R.id.verify_otp_button)
    Button verify;
    String verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        StartFirebaseLogin();
          SharedPref sharedPref=new SharedPref(this);
          String mobilenumber=sharedPref.getMobile();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+mobilenumber,                     // Phone number to verify
                60,                           // Timeout duration
                TimeUnit.SECONDS,                // Unit of timeout
                this,        // Activity (for callback binding)
                mCallback);


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String otpn = otp.getText().toString().trim();

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otpn);
                SigninWithPhone(credential);


            }

            private void SigninWithPhone(PhoneAuthCredential credential) {
                mauth.signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(OtpActivity.this, "Verifiction Done", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(OtpActivity.this, HomePageActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(OtpActivity.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }


        });
    }



    private void StartFirebaseLogin() {

        mauth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                SharedPreferences.Editor editor = getSharedPreferences("IS_PHONE_VERIFIED", MODE_PRIVATE).edit();
                  editor.putBoolean("phone verify",true);
                editor.apply();

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(OtpActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(OtpActivity.this,"Code sent",Toast.LENGTH_SHORT).show();
            }
        };
    }
}
