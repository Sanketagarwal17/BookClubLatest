package com.example.android.bookclublatest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    @BindView(R.id.btn_issue_book)
    Button issueBookButton;
    int send = 1;
    private static final String TAG = "IssueBookDetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_book_detail);
      final String book = getIntent().getStringExtra("book");
        final String author = getIntent().getStringExtra("author");
        final String isbn = getIntent().getStringExtra("isbn");
        final String hardsofy = getIntent().getStringExtra("hardsofy");
        final String ism = getIntent().getStringExtra("ism");
        final String publisher = getIntent().getStringExtra("publisher");
        final String tags = getIntent().getStringExtra("tags");
        ButterKnife.bind(this);
        tvBook.setText(book);
        tvAuthor.setText(author);
        tvHardSofy.setText(hardsofy);
        tvIsbn.setText(isbn);
        tvIsm.setText(ism);
        tvPub.setText(publisher);
        tvTags.setText(tags);
        issueBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser()== null)
                    Toast.makeText(IssueBookDetailActivity.this, "Please login first", Toast.LENGTH_SHORT).show();
                else
                {

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Issue Requests").
                            child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Books");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                            {
                                Log.d(TAG, "onDataChange: " + dataSnapshot1.child("Client Gmail").getValue());
                                if(dataSnapshot1.getKey().equals(isbn) )
                                {
                                    IssueBookDetailActivity.this.setSend(0);
                                    break;

                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    if(send != 0) {
                        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        FirebaseDatabase.getInstance().getReference().child("Issue Requests").child(uid)
                                .child("Phone").setValue(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
                        FirebaseDatabase.getInstance().getReference().child("Issue Requests").child(uid)
                                .child("Email").setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                        //FirebaseDatabase.getInstance().getReference().child("Issue Requests").child(uid).child("Books")
                          //      .child("ISBN").setValue(isbn);
                        FirebaseDatabase.getInstance().getReference().child("Issue Requests").child(uid).child("Books") .child(isbn)
                                .child("Hardsofy").setValue(hardsofy);
                        Toast.makeText(IssueBookDetailActivity.this, book + "requested for you", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(IssueBookDetailActivity.this, "You have already requested for this book please wait while we process your request", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    public void setSend(int send) {
        this.send = send;
    }

    public int getSend() {
        return send;
    }
}
