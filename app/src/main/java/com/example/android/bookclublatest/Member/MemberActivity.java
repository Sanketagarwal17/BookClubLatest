package com.example.android.bookclublatest.Member;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.bookclublatest.Member.AddBook.AddBookActivity;
import com.example.android.bookclublatest.Member.ConfirmIssue.ConfirmIssueActivity;
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
    @BindView(R.id.addbook)
    Button addBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        ButterKnife.bind(this);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemberActivity.this, AddBookActivity.class));
            }
        });

        issue_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemberActivity.this, ConfirmIssueActivity.class));
            }
        });
    }
}
