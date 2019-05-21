package com.example.android.bookclublatest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IssueBookDetailActivity extends AppCompatActivity {

    @BindView(R.id.det_book)
    TextView tvBook;
    @BindView(R.id.det_author)
    TextView tvAuthor;
    @BindView(R.id.det_hardsofy)
    TextView tvHardSofy;
    @BindView(R.id.det_isbn)
    TextView tvIsbn;
    @BindView(R.id.det_ism)
    TextView tvIsm;
    @BindView(R.id.det_pub)
    TextView tvPub;
    @BindView(R.id.det_tags)
    TextView tvTags;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_book_detail);
      String book = getIntent().getStringExtra("book");
        String author = getIntent().getStringExtra("author");
        String isbn = getIntent().getStringExtra("isbn");
        String hardsofy = getIntent().getStringExtra("hardsofy");
        String ism = getIntent().getStringExtra("ism");
        String publisher = getIntent().getStringExtra("publisher");
        String tags = getIntent().getStringExtra("tags");
        ButterKnife.bind(this);
        tvBook.setText(book);
        tvAuthor.setText(author);
        tvHardSofy.setText(hardsofy);
        tvIsbn.setText(isbn);
        tvIsm.setText(ism);
        tvPub.setText(publisher);
        tvTags.setText(tags);


    }
}
