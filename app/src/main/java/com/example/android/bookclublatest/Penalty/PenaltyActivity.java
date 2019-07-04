package com.example.android.bookclublatest.Penalty;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.bookclublatest.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PenaltyActivity extends AppCompatActivity {

@BindView(R.id.penalty_recycler)
    RecyclerView recyclerView;
    //PenaltyContract.Presenter<PenaltyContract.View>mPresenter;



PenaltyModel penaltyModel;
PenaltyAdapter penaltyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penalty);

        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    //    mPresenter=new PenaltyPresenter<PenaltyContract.View>();
      //  ((PenaltyPresenter<PenaltyContract.View>)mPresenter).onAttach((PenaltyContract.View) PenaltyActivity.this);


        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Issue History");
        final String email1=FirebaseAuth.getInstance().getCurrentUser().getEmail();
       // final  String email=email1.replace('.',',');
        final  String email="rishabh997,18je0676@am,iitism,ac,in";

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
              {
                  List<PenaltyModel> penaltyModelList=new ArrayList<>();
                  Log.e("checkin",dataSnapshot1.getKey().toString());
                  Log.e("checkinm", String.valueOf((boolean)dataSnapshot1.getKey().equals(email)));
                   if(dataSnapshot1.getKey().equals(email))
                   {
                       for(DataSnapshot dataSnapshot2: dataSnapshot1.getChildren()) {
                           String issue_date = dataSnapshot2.child("issue_date").getValue().toString();
                           String return_date = dataSnapshot2.child("return_date").getValue().toString();
                           String bookname=dataSnapshot2.child("bookname").getValue().toString();
                           String isbn=dataSnapshot2.child("isbn").getValue().toString();
                           Log.e("issue", bookname);
                           penaltyModel=new PenaltyModel(issue_date,return_date,isbn,bookname);
                           penaltyModelList.add(penaltyModel);
                           Log.e("issuech", String.valueOf(penaltyModelList.size()));
                       }
                       penaltyAdapter=new PenaltyAdapter(penaltyModelList,PenaltyActivity.this);
                       penaltyAdapter.notifyDataSetChanged();
                       recyclerView.setAdapter(penaltyAdapter);
                   }

              }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });












    }













}
