package com.example.android.bookclublatest.Member.ConfirmIssue.ConfirmProceed;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookclublatest.Member.ConfirmIssue.ConfirmIssueActivity;
import com.example.android.bookclublatest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProceedActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
{
    @BindView(R.id.proceed_checkbox)
    CheckBox checkBox;
    @BindView(R.id.proceed_scan)
    TextView scan;
    @BindView(R.id.proceed_code)
    TextView unique_code;
    @BindView(R.id.proceed_date)
    TextView date;
    @BindView(R.id.proceed_date_image)
    ImageView dtae_image;
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

    IntentIntegrator intentIntegrator;
    DatePickerDialog datePickerDialog;
    final static String[] months={"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};

    String email,bookname,isbn,ism,issue_date,return_date,ism_code,hard_soft="Hard Copy";

    int correct=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceed);
        intentIntegrator=new IntentIntegrator(this);
        ButterKnife.bind(this);

        email=getIntent().getStringExtra("Email");
        bookname=getIntent().getStringExtra("Book");
        isbn=getIntent().getStringExtra("isbn");
        ism=getIntent().getStringExtra("ism");

        Calendar calendar=Calendar.getInstance(TimeZone.getDefault());
        datePickerDialog = new DatePickerDialog(this,this,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentIntegrator.initiateScan();
            }
        });

        dtae_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        cname.setText(bookname);
        cemail.setText(email);
        cisbn.setText(isbn);

        Calendar calendar2=Calendar.getInstance(TimeZone.getDefault());
        String current=calendar2.get(Calendar.DAY_OF_MONTH) + " " + months[(calendar2.get(Calendar.MONTH))]
                + "," + calendar2.get(Calendar.YEAR);
        issue_date=current;
        cissue.setText(current);

        issue_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(date.getText().toString().equals("Select Date") || unique_code.getText().toString().equals(""))
                {
                    Toast.makeText(ProceedActivity.this, "Make Sure that all credentials are Checked", Toast.LENGTH_SHORT).show();
                }
                else
                    checkCode();
            }
        });
    }

    private void checkCode()
    {
        if(checkBox.isChecked())
            hard_soft="Hard Copy";
        else
            hard_soft="Soft Copy";

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Books_List");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds1:dataSnapshot.getChildren())
                {
                    if(ds1.getKey().equals(isbn))
                    {
                        Log.i("isbn",isbn);
                        for(DataSnapshot ds2:ds1.getChildren())
                        {
                            if(ds2.getKey().equals(hard_soft))
                            {
                                Log.i("Type",hard_soft);
                                for(DataSnapshot ds3:ds2.getChildren())
                                {
                                    if(ds3.getKey().equals(ism_code))
                                    {
                                        Log.i("ismcode is ",ism_code);
                                        ProceedActivity.this.setCorrect(1);
                                        break;
                                    }
                                }
                            }
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Log.i("Here after setter",correct+"");
        if(correct==1)
        {
            updateDatabase();
        }
        else
        {
            Toast.makeText(this, "Try Again  OR \nThe Scanned Code didn't matched with any of the Books in Database", Toast.LENGTH_LONG).show();
        }
    }

    private void setCorrect(int correct)
    {
        Log.i("Here at setter",correct+"");
        this.correct=correct;
    }

    private void updateDatabase()
    {
        //update request
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Issue Requests");
        databaseReference.child(email).child(ism).child("Status").setValue("Issued");

        //Create Issue History
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("Issue History");

        ProceedModel model=new ProceedModel(bookname,isbn,issue_date,return_date,"Not Returned",ism_code,"pending");
        reference.child(email).child(ism).setValue(model);

        Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show();

        finish();
        startActivity(new Intent(this, ConfirmIssueActivity.class));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String message=dayOfMonth+" "+months[month]+","+year;
        return_date=message;
        date.setText(message);
        creturn.setText(message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null)
        {
            if(result.getContents()!=null)
            {
                    unique_code.setText(result.getContents());
                    cism.setText(result.getContents());
                    ism_code=result.getContents();
            }
            else
                Toast.makeText(this, "Error Scanning. Try Again !", Toast.LENGTH_SHORT).show();
        }
    }
}