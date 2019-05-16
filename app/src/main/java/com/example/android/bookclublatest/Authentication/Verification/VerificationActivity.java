package com.example.android.bookclublatest.Authentication.Verification;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.R;

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

    VerificationContract.Presenter<VerificationContract.View> mpresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        ButterKnife.bind(this);

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
               mpresenter.getPhone();
           }
       });
    }

    @Override
    public void showEmail(String string)
    {
        email_button_get.setText(string);
    }

    @Override
    public void showPhone(String string) {
        phone_button_get.setText(string);
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
}
