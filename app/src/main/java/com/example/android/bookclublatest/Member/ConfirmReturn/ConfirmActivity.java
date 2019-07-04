package com.example.android.bookclublatest.Member.ConfirmReturn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.bookclublatest.Member.ConfirmIssue.ConfirmProceed.ProceedActivity;
import com.example.android.bookclublatest.Member.ConfirmReturn.ReturnProceed.ProceedReturnActivity;
import com.example.android.bookclublatest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmActivity extends AppCompatActivity implements ConfirmAdapter.Clicklistener
{
    @BindView(R.id.confirm_return_recycler)
    RecyclerView recyclerView;

    List<ConfirmModel> list=new ArrayList<>();
    ConfirmAdapter adapter;

    String email,status,isbn,ism,bookname,issuedate,returndate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_return_book);
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Return Requests");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot ds1:dataSnapshot.getChildren())
                {
                    email=ds1.getKey();
                    for(DataSnapshot ds2:ds1.getChildren())
                    {
                        status=ds2.child("status").getValue().toString();
                        if(status.equals("pending"))
                        {
                            bookname=ds2.child("bookname").getValue().toString();
                            isbn=ds2.child("isbn").getValue().toString();
                            ism=ds2.child("ismcode").getValue().toString();
                            issuedate=ds2.child("issue_date").getValue().toString();
                            returndate=ds2.child("return_date").getValue().toString();
                            status=ds2.child("status").getValue().toString();

                            ConfirmModel model=new ConfirmModel(email,bookname,isbn,ism,issuedate,returndate,status);
                            list.add(model);
                        }
                    }
                }
                adapter=new ConfirmAdapter(list, ConfirmActivity.this,ConfirmActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void proceed(int pos) {
        Intent intent=new Intent(this, ProceedReturnActivity.class);
        intent.putExtra("book_details", (Serializable) list.get(pos));
        finish();
        startActivity(intent);
    }
}
