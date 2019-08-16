package com.example.android.bookclublatest.ContactUs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookclublatest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactUsActivity extends AppCompatActivity
{

    @BindView(R.id.contactemailtext)
    TextView email_text;
    @BindView(R.id.contact_mobile_text)
    TextView mobile_text;
    @BindView(R.id.contact_phone_text)
    TextView phone_text;
    @BindView(R.id.contactemailbutton)
    TextView email_button;
    @BindView(R.id.contact_mobile_button)
    TextView mobile_button;
    @BindView(R.id.contact_phone_button)
    TextView phone_button;
    @BindView(R.id.return_home)
    ImageView home;
    @BindView(R.id.textView26)
    TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus_main);
        ButterKnife.bind(this);


        email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
        mobile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callmobile();
            }
        });
        phone_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone();
            }
        });


        title.setText("Contact Us");
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void sendEmail()
    {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        String mailTo = "mailto:".concat(email_text.getText().toString());
        i.setData(Uri.parse(mailTo));
        try {
            startActivity(i);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
    public void callmobile()
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", String.valueOf(mobile_text.getText().toString()), null));
        startActivity(intent);
    }
    public void callPhone()
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", String.valueOf(phone_text.getText().toString()), null));
        startActivity(intent);
    }
}