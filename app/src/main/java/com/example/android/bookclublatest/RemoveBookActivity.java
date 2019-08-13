package com.example.android.bookclublatest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.bookclublatest.HomePage.HomePageActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RemoveBookActivity extends AppCompatActivity {

    @BindView(R.id.edt_txt_isbn)
    EditText isbn;
    @BindView(R.id.edt_txt_ism)
    EditText ism;
    @BindView(R.id.btn_remove)
    Button remove;
//    @BindView(R.id.spinner)
//    Spinner spinner;
    String xisbn,xism;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_book);
        ButterKnife.bind(this);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Books_List");

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xisbn=isbn.getText().toString().trim();
                xism=ism.getText().toString().trim();

              databaseReference.child(xisbn).child("Hard Copy").child(xism).removeValue();
              databaseReference.child(xisbn).child("Soft Copy").child(xism).removeValue();
                Intent i=new Intent(getApplicationContext(), HomePageActivity.class);
                finish();
                startActivity(i);
            }
        });
    }
}
