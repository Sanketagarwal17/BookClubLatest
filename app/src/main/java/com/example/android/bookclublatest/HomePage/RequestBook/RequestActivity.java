package com.example.android.bookclublatest.HomePage.RequestBook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.bookclublatest.Authentication.Login.LoginActivity;
import com.example.android.bookclublatest.Authentication.Verification.VerificationActivity;
import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.Member.AddBook.AddBookActivity;
import com.example.android.bookclublatest.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestActivity extends BaseActivity
        implements RequestPageContract.View {

    @BindView(R.id.request_book_name)
    EditText book;
    @BindView(R.id.request_author_name)
    EditText author;
    @BindView(R.id.request_publication_name)
    EditText publication;
    @BindView(R.id.request_button)
    Button submit;

    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

    RequestPageContract.Presenter<RequestPageContract.View> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_book);
        presenter= new RequestPagePresenter<>();
        ButterKnife.bind(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(firebaseUser.isEmailVerified())
                    presenter.submit(book.getText().toString().trim(),author.getText().toString().trim(),publication.getText().toString().trim());
                else
                    Toast.makeText(RequestActivity.this, "Please Verify Your email first", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
