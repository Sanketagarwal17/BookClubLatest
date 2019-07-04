package com.example.android.bookclublatest.HomePage.History;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.bookclublatest.R;
import com.example.android.bookclublatest.SharedPref.SharedPref;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity
{
    @BindView(R.id.recycler_issue_history)
    RecyclerView recyclerView;
    @BindView(R.id.email_history)
    TextView emailtext;

    HistoryAdapter adapter;
    List<HistoryModel> list=new ArrayList<>();
    SharedPref sharedPref;

    String email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_history);
        ButterKnife.bind(this);
        sharedPref=new SharedPref(this);
        email=sharedPref.getEmail();
        emailtext.setText(email);
        email=email.replace('.',',');

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Issue History");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    if(ds.getKey().equals(email))
                    {
                        for(DataSnapshot ds2:ds.getChildren())
                        {
                            HistoryModel model=ds2.getValue(HistoryModel.class);
                            list.add(model);
                        }
                    }
                }
                adapter=new HistoryAdapter(HistoryActivity.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
