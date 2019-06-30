package com.example.android.bookclublatest.Member;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.android.bookclublatest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberActivity extends AppCompatActivity {

    @BindView(R.id.request_confirm)
    Button request_book;
    @BindView(R.id.issue_confirm)
    Button issue_book;
    @BindView(R.id.return_confirm)
    Button return_book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        ButterKnife.bind(this);
    }




}
