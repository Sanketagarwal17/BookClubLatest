package com.example.android.bookclublatest.Authentication.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.android.bookclublatest.Authentication.ForgetPassword.ForgetPasswordActivity;
import com.example.android.bookclublatest.Admin.RequestsForMember.AdminActivity.AdminActivity;
import com.example.android.bookclublatest.Authentication.SignUp.SignUpActivity;
import com.example.android.bookclublatest.Authentication.SignUp.SignUpModel;
import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.BuildConfig;
import com.example.android.bookclublatest.HomePage.HomePageActivity;
import com.example.android.bookclublatest.Member.MemberActivity;
import com.example.android.bookclublatest.R;
import com.example.android.bookclublatest.SharedPref.SharedPref;
import com.example.android.bookclublatest.Student.StudentActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.provider.Telephony.Carriers.PASSWORD;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.login_email)
    EditText email;

    @BindView(R.id.login_password)
    EditText password;

    @BindView(R.id.sign_up)
    TextView signup;

//    @BindView(R.id.sign_up2)
//    TextView signup2;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.forget_password)
    TextView forgetpassword;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference("users");
    SignUpModel signUpModel;
    SharedPref sharedPref;

    LoginContract.Presenter<LoginContract.View> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharedPref= new SharedPref(this);
        mPresenter = new LoginPresenter<LoginContract.View>();
        ((LoginPresenter<LoginContract.View>) mPresenter).onAttach(this);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            if (BuildConfig.FLAVOR.equals("admin")) {
                startActivityUtil(AdminActivity.class);
            } else if (BuildConfig.FLAVOR.equals("member")) {
                startActivityUtil(MemberActivity.class);
            } else {
                startActivityUtil(HomePageActivity.class);
            }
            finish();


        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (user.equalsIgnoreCase("")) {
                    email.setError("can't be blank");
                    email.requestFocus();
                } else if (PASSWORD.equalsIgnoreCase("")) {
                    password.setError("can't be blank");
                    password.requestFocus();
                } else {
                    showLoading();
                    mPresenter.doLogin(user, pass);
                    hideLoading();
                }
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });

//        signup2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
//                finish();
//            }
//
//        });

        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
            }
        });

    }


    private void startActivityUtil(Class activity) {
        Intent intent = new Intent(LoginActivity.this, activity);
        startActivity(intent);
        finish();
    }


    @Override
    public void showloginResult() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String email1=email.getText().toString().trim().replace('.',',');
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    if(ds.getKey().equals(email1))
                    {
                        signUpModel = ds.getValue(SignUpModel.class);
                        sharedPref.setUsername(signUpModel.getName());
                        sharedPref.setEmail(signUpModel.getEmail());
                        sharedPref.setMobile(signUpModel.getPhonenumber());
                        sharedPref.setAdmission(signUpModel.getAdmissionnumber());
                        sharedPref.setAccessLevel(signUpModel.getStatus());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        if (BuildConfig.FLAVOR.equals("admin")) {
            startActivityUtil(AdminActivity.class);
        } else if (BuildConfig.FLAVOR.equals("member")) {
            startActivityUtil(MemberActivity.class);
        } else {
            startActivityUtil(HomePageActivity.class);
        }
    }

    @Override
    public void errorOnLoading(String error) {
        Toast.makeText(LoginActivity.this, "OOPS Something Wrong Happen" + error, Toast.LENGTH_LONG).show();

    }


}
