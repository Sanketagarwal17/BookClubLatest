package com.example.android.bookclublatest.Member.AddBook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.bookclublatest.Authentication.Login.LoginActivity;
import com.example.android.bookclublatest.Authentication.Verification.VerificationActivity;
import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddBookActivity extends BaseActivity implements AddBookContract.View {

    private static final String TAG = "AddBookActivity";
    @BindView(R.id.add_bookname)
    EditText name;
    @BindView(R.id.add_author)
    EditText author;
    @BindView(R.id.add_pulication)
    EditText publisher;
    @BindView(R.id.add_isbn)
    Button ISBN;
    @BindView(R.id.add_ism)
    Button ISM;
    @BindView(R.id.add_tags)
    EditText tags;
    @BindView(R.id.add_submit)
    Button submit;
    @BindView(R.id.add_hard_soft)
    CheckBox checkBox;
    @BindView(R.id.ism_added)
    Button ismAddedBtn;
    @BindView(R.id.isbn_added)
    Button isbnAdded;

    private int check=0;
    private String mname,mauthor,mpublication,misbn,mism,mtags,mchecbox;

    IntentIntegrator intentIntegrator;
    AddBookContract.Presenter<AddBookContract.View> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        ButterKnife.bind(this);
        presenter=new AddBookPresenter<>();
        presenter.onAttach(this);
        intentIntegrator=new IntentIntegrator(this);
        ISBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check=1;
                intentIntegrator.initiateScan();
            }
        });
        ISM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check=2;
                intentIntegrator.initiateScan();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mname=name.getText().toString();
                mauthor=author.getText().toString();
                mpublication=publisher.getText().toString();
                mtags=tags.getText().toString();
                if(checkBox.isChecked())
                    mchecbox="Hard Copy";
                else
                    mchecbox="Soft Copy";
                Log.d(TAG, "onClick:" + mname +" " + mauthor+" " + mpublication+" " +misbn+" " + mism+" " +mtags+" " +mchecbox);
                if(misbn == null)
                {
                    misbn = "null";
                }
                if(mism == null)
                {
                    mism = "null" ;
                }
                presenter.submit(mauthor,mname,mchecbox,misbn,mism,mpublication,mtags);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null)
        {
            if(result.getContents()!=null)
            {
                if(check==1)
                {
                    isbnAdded.setVisibility(View.VISIBLE);
                    ISBN.setText(result.getContents());
                    misbn=result.getContents();
                }
                if(check==2)
                {
                    ismAddedBtn.setVisibility(View.VISIBLE);
                    ISM.setText(result.getContents());
                    mism=result.getContents();
                }
            }
            else
                Toast.makeText(this, "Error Scanning. Try Again !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
