package com.example.android.bookclublatest.Member.ConfirmReturn.ReturnProceed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.android.bookclublatest.Member.ConfirmReturn.ConfirmActivity;
import com.example.android.bookclublatest.Member.ConfirmReturn.ConfirmModel;
import com.example.android.bookclublatest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class ProceedReturnActivity extends AppCompatActivity
{
    ConfirmModel model;

    @BindView(R.id.proceed_scan)
    ConstraintLayout scan;
    @BindView(R.id.proceed_code)
    TextView unique_code;
    @BindView(R.id.proceed_issue)
    Button issue_final;
    @BindView(R.id.confirm_email)
    TextView cemail;
    @BindView(R.id.confirm_name)
    TextView cname;
    @BindView(R.id.confirm_isbn)
    TextView cisbn;
    @BindView(R.id.confirm_ism)
    TextView cism;
    @BindView(R.id.confirm_issue)
    TextView cissue;
    @BindView(R.id.confirm_return)
    TextView creturn;
    @BindView(R.id.confirm_today)
    TextView ctoday;
    @BindView(R.id.book_photo_proceedreturn)
    ImageView imageView;

    @BindView(R.id.return_home)
    ImageView home;
    @BindView(R.id.textView26)
    TextView title;

    IntentIntegrator intentIntegrator;
    final static String[] months={"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};
    String current;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceed_return);
        ButterKnife.bind(this);
        intentIntegrator=new IntentIntegrator(this);
        model=(ConfirmModel) getIntent().getExtras().getSerializable("book_details");

        title.setText("Confirm Return");
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        cemail.setText(model.getEmail());
        cname.setText(model.getBookname());
        cisbn.setText(model.getIsbn());
        cism.setText(model.getIsmcode());
        cissue.setText(model.getIssue_date());
        creturn.setText(model.getReturn_date());
        Picasso.get().load(model.getUrl()).into(imageView);
        Calendar calendar2=Calendar.getInstance(TimeZone.getDefault());
        current=calendar2.get(Calendar.DAY_OF_MONTH) + " " + months[(calendar2.get(Calendar.MONTH))]
                + "," + calendar2.get(Calendar.YEAR);
        ctoday.setText(current);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentIntegrator.initiateScan();
            }
        });

        issue_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(model.getIsmcode().equals(unique_code.getText().toString()))
                {
                    updateDatabase();
                }
                else
                    Toasty.error(ProceedReturnActivity.this, "The Scanned Code does not matches with Issued Code", Toast.LENGTH_SHORT,false).show();
            }
        });
    }

    private void updateDatabase()
    {
        //update return request
        String email=model.getEmail().replace('.',',');
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Return Requests");
        databaseReference.child(email).child(model.getIsmcode()).child("status").setValue("returned");

        /**
         * Doing History part for returning multiple books of same ism code after different
         */
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference("Issue History").child(email);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(        ds.child("bookname").getValue().toString().equals(model.getBookname())
                            || ds.child("isbn").getValue().toString().equals(model.getIsbn())
                            || ds.child("ism").getValue().toString().equals(model.getIsmcode())
                            || ds.child("issue_date").getValue().toString().equals(model.getIssue_date())
                            || ds.child("return_date").getValue().toString().equals(model.getReturn_date())
                            || ds.child("status").getValue().toString().equals("Not Returned")
                            || ds.child("book_returned_on").getValue().toString().equals("pending"))
                    {
                        reference.child(ds.getKey()).child("status").setValue("Returned");
                        reference.child(ds.getKey()).child("book_returned_on").setValue(current);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Toasty.success(this, "Successfully Returned", Toast.LENGTH_SHORT,true).show();
        finish();
        startActivity(new Intent(this, ConfirmActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null)
        {
            if(result.getContents()!=null)
            {
                unique_code.setText(result.getContents());
            }
            else
                Toast.makeText(this, "Error Scanning. Try Again !", Toast.LENGTH_SHORT).show();
        }
    }
}