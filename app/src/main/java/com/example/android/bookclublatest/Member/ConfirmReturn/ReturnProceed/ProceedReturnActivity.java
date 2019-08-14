package com.example.android.bookclublatest.Member.ConfirmReturn.ReturnProceed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.bookclublatest.Member.ConfirmReturn.ConfirmActivity;
import com.example.android.bookclublatest.Member.ConfirmReturn.ConfirmModel;
import com.example.android.bookclublatest.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProceedReturnActivity extends AppCompatActivity
{
    ConfirmModel model;

    @BindView(R.id.proceed_scan)
    TextView scan;
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

    IntentIntegrator intentIntegrator;
    final static String[] months={"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceed_return);
        ButterKnife.bind(this);
        intentIntegrator=new IntentIntegrator(this);
        model=(ConfirmModel) getIntent().getExtras().getSerializable("book_details");

        cemail.setText(model.getEmail());
        cname.setText(model.getBookname());
        cisbn.setText(model.getIsmcode());
        cism.setText(model.getIsmcode());
        cissue.setText(model.getIssue_date());
        creturn.setText(model.getReturn_date());
        Calendar calendar2=Calendar.getInstance(TimeZone.getDefault());
        String current=calendar2.get(Calendar.DAY_OF_MONTH) + " " + months[(calendar2.get(Calendar.MONTH))]
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
                    Toast.makeText(ProceedReturnActivity.this, "The Scanned Code does not matches with Issued Code", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateDatabase()
    {
        //update return request
        String email=model.getEmail().replace('.',',');
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Return Requests");
        databaseReference.child(email).child(model.getIsbn()).child("status").setValue("returned");

        //update issue history
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("Issue History");
        reference.child(email).child(model.getIsmcode()).child("status").setValue("returned");

        Toast.makeText(this, "Successfully Returned", Toast.LENGTH_SHORT).show();
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