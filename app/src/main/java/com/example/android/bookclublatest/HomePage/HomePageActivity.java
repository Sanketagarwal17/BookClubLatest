package com.example.android.bookclublatest.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.bookclublatest.AboutClub.AboutUsActivity;
import com.example.android.bookclublatest.Admin.RequestsForMember.RequestsForMember;
import com.example.android.bookclublatest.Authentication.ChangePassword.ChangePasswordActivity;
import com.example.android.bookclublatest.Authentication.Login.LoginActivity;
import com.example.android.bookclublatest.Authentication.SignUp.SignUpModel;
import com.example.android.bookclublatest.Authentication.Verification.VerificationActivity;
import com.example.android.bookclublatest.ContactUs.ContactUsActivity;
import com.example.android.bookclublatest.Developers.DevelopersActivity;
import com.example.android.bookclublatest.Faq.MVP.FAQActivity;
import com.example.android.bookclublatest.HomePage.RequestBook.RequestActivity;
import com.example.android.bookclublatest.HomePage.Return.ReturnActivity;
import com.example.android.bookclublatest.IssueBook;
import com.example.android.bookclublatest.Member.MemberActivity;
import com.example.android.bookclublatest.Penalty.PenaltyActivity;
import com.example.android.bookclublatest.Profile.ProfileActivity;
import com.example.android.bookclublatest.R;
import com.example.android.bookclublatest.RequestForMember.RequestForMemberActivity;
import com.example.android.bookclublatest.SharedPref.SharedPref;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import ss.com.bannerslider.Slider;

public class HomePageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MainSliderAdapter MainSliderAdapter = new MainSliderAdapter();
    PicassoImageLoadingService PicassoImageLoadingService =new PicassoImageLoadingService(HomePageActivity.this);
    FirebaseAuth firebaseAuth;
    @BindView(R.id.request)
    Button request;
    @BindView(R.id.issue)
    Button issue;
    @BindView(R.id.history)
    Button history;
    @BindView(R.id.returnbook)
    Button returnbook;

    @BindView(R.id.banner_slider)
    Slider banner_slider;

    SharedPref sharedPref;

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference("Status");
    SignUpModel signUpModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPref=new SharedPref(this);
        ButterKnife.bind(this);
        Slider.init(PicassoImageLoadingService);
        banner_slider.setAdapter(MainSliderAdapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePageActivity.this,RequestsForMember.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, IssueBook.class));
            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, RequestActivity.class));
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, ProfileActivity.class));
            }
        });

        returnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, ReturnActivity.class));
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String email=sharedPref.getEmail();
                String email1=email.replace('.',',');
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    if(ds.getKey().equals(email1))
                    {
                        String status=ds.getValue().toString();
                        sharedPref.setAccessLevel(status);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_verify)
        {
            startActivity(new Intent(this, VerificationActivity.class));
        }
        if(id==R.id.action_logout)
        {
            firebaseAuth=FirebaseAuth.getInstance();
            firebaseAuth.signOut();

            sharedPref.setAdmission("");
            sharedPref.setMobile("");
            sharedPref.setUsername("");
            sharedPref.setEmail("");

            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_requestmenmber)
        {
            if(!sharedPref.getAccessLevel().equals("Member"))
                startActivity(new Intent(this,RequestForMemberActivity.class));
            else
                Toast.makeText(this, "You are already a member", Toast.LENGTH_SHORT).show();

        }

        else if (id == R.id.nav_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
        }
        else if(id == R.id.nav_penalty)
            startActivity(new Intent(this,PenaltyActivity.class));

        else if(id==R.id.nav_faq)
            startActivity(new Intent(this, FAQActivity.class));
        else if(id==R.id.nav_contact)
            startActivity(new Intent(this, ContactUsActivity.class));
        else if(id==R.id.nav_aboutus)
            startActivity(new Intent(this, AboutUsActivity.class));
        else if(id==R.id.nav_developers)
            startActivity(new Intent(this, DevelopersActivity.class));
        else  if(id==R.id.nav_chnage_password) {
            startActivity(new Intent(this, ChangePasswordActivity.class));

        }
        else if(id==R.id.nav_member_page)
        {
            if(sharedPref.getAccessLevel().equals("Member"))
                startActivity(new Intent(this, MemberActivity.class));
            else
                Toast.makeText(this, "You are not Authorized to access it", Toast.LENGTH_LONG).show();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
