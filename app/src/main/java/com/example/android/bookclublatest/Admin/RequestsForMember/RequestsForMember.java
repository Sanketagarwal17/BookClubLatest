package com.example.android.bookclublatest.Admin.RequestsForMember;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.HomePage.HomePageActivity;
import com.example.android.bookclublatest.R;
import com.example.android.bookclublatest.RequestForMember.RequestForMemberActivity;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestsForMember extends BaseActivity implements RequetsForMemberContract.View {

@BindView(R.id.allrequestformember)
RecyclerView allrequestformember;

@BindView(R.id.return_home)
ImageView home;
@BindView(R.id.textView26)
TextView heading;

ArrayList<RequetsForMemberModel> arrayList;

RequestsForMemberAdapter requestsForMemberAdapter;
DatabaseReference databaseReference;
RequetsForMemberContract.Presenter<RequetsForMemberContract.View> mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_request_for_member);
        mPresenter=new RequetsForMemberPresenter<>();
        ButterKnife.bind(this);

        arrayList=new ArrayList<>();
        allrequestformember.setHasFixedSize(false);
        allrequestformember.setLayoutManager(new LinearLayoutManager(this));

        databaseReference=FirebaseDatabase.getInstance().getReference("requstsformember");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                   String eamil = ds.getValue().toString();
                   int index=eamil.indexOf('@');
                   String before=eamil.substring(0,index);
                   String after =eamil.substring(index);
                   eamil = before +"\n\t\t"+after;
                   RequetsForMemberModel requetsForMemberModel = new RequetsForMemberModel(eamil);
                   arrayList.add(requetsForMemberModel);
                }
                requestsForMemberAdapter=new RequestsForMemberAdapter(RequestsForMember.this,arrayList);
                allrequestformember.setAdapter(requestsForMemberAdapter);
                requestsForMemberAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        heading.setText("Membership");
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RequestsForMember.this, HomePageActivity.class));
                finish();
            }
        });

    }
}
