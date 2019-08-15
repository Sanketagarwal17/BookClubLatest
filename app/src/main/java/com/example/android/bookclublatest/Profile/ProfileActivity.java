package com.example.android.bookclublatest.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.HomePage.History.HistoryActivity;
import com.example.android.bookclublatest.HomePage.History.HistoryAdapter;
import com.example.android.bookclublatest.HomePage.History.HistoryModel;
import com.example.android.bookclublatest.HomePage.HomePageActivity;
import com.example.android.bookclublatest.HomePage.RequestBook.RequestActivity;
import com.example.android.bookclublatest.R;
import com.example.android.bookclublatest.SharedPref.SharedPref;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends BaseActivity implements ProfileContract.View
{
    ProfileContract.Presenter<ProfileContract.View> mpresenter;

    @BindView(R.id.profile_name)
    TextView name;
    @BindView(R.id.profile_email)
    TextView email;
    @BindView(R.id.profile_admision)
    TextView admission;
    @BindView(R.id.profile_status)
    TextView status;
    @BindView(R.id.profile_verify_email)
    Button verify;
    @BindView(R.id.profile_mobile)
    TextView mobile;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.no_books)
    ConstraintLayout constraintLayout;
    @BindView(R.id.textView13)
    TextView no_of_books;
    @BindView(R.id.circularImageView)
    CircularImageView circularImageView;
    @BindView(R.id.imageView3)
    ImageView home;
    HistoryAdapter adapter;
    List<HistoryModel> list=new ArrayList<>();
    SharedPref sharedPref;

    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        ButterKnife.bind(this);
        mpresenter = new ProfilePresenter<>();
        sharedPref=new SharedPref(this);
        status.setText(sharedPref.getAccessLevel());
        mobile.setText(sharedPref.getMobile());
        admission.setText(sharedPref.getAdmission());
        email.setText(sharedPref.getEmail());
        name.setText(sharedPref.getUsername());

        if(sharedPref.getImageUrl().isEmpty())
            url="www.bookclub.com/";
        else
            url=sharedPref.getImageUrl();

        Picasso.get().load(url).into(circularImageView);
/*
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified())
                    Toast.makeText(ProfileActivity.this, "You are already Email Verified", Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(ProfileActivity.this, VerificationActivity.class));
            }
        });*/

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Issue History");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    if(ds.getKey().equals(sharedPref.getEmail().replace('.',',')))
                    {
                        for(DataSnapshot ds2:ds.getChildren())
                        {
                            HistoryModel model=ds2.getValue(HistoryModel.class);
                            list.add(model);
                        }
                    }
                }
                if(list.size()>0) {
                    constraintLayout.setVisibility(View.GONE);
                    no_of_books.setText(list.size()+"");
                    adapter = new HistoryAdapter(ProfileActivity.this, list);
                    recyclerView.setAdapter(adapter);
                }
                else
                {
                    constraintLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
