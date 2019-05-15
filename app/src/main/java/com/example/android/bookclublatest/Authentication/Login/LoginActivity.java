package com.example.android.bookclublatest.Authentication.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookclublatest.Admin.AdminActivity;
import com.example.android.bookclublatest.Authentication.ForgetPassword.ForgetPassword;
import com.example.android.bookclublatest.Authentication.SignUp.SignUpActivity;
import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.BuildConfig;
import com.example.android.bookclublatest.Member.MemberActivity;
import com.example.android.bookclublatest.R;
import com.example.android.bookclublatest.Student.StudentActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

    @BindView(R.id.sign_up2)
     TextView signup2;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.forget_password)
    TextView forgetpassword;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    LoginContract.Presenter<LoginContract.View> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
       // getActivityComponent().inject(this);
        //mPresenter.onAttach(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {  finish();
            if(BuildConfig.FLAVOR.equals("admin"))
            { startActivityUtil(AdminActivity.class); }

            else if(BuildConfig.FLAVOR.equals("member"))
            { startActivityUtil(MemberActivity.class); }

            else
            { startActivityUtil(StudentActivity.class); }

        }





    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String user = email.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (user.equalsIgnoreCase(""))
            {
                email.setError("can't be blank");
            }
            else if (PASSWORD.equalsIgnoreCase(""))
            {
                password.setError("can't be blank");
            }

            else {
                showLoading();
                mPresenter.doLogin(user,pass);
                hideLoading();
            }
        }
    });


    signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        }
    });

    signup2.setOnClickListener(new View.OnClickListener() {
                                   @Override
       public void onClick(View v) {
            finish();
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        }

    });

    forgetpassword.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              startActivity(new Intent(LoginActivity.this, ForgetPassword.class));
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

        if(BuildConfig.FLAVOR.equals("admin"))
        { startActivityUtil(AdminActivity.class); }

        else if(BuildConfig.FLAVOR.equals("member"))
        { startActivityUtil(MemberActivity.class); }

        else
        { startActivityUtil(StudentActivity.class); }

    }

    @Override
    public void errorOnLoading(String error) {
        Toast.makeText(LoginActivity.this,"OOPS Something Wrong Happen"+error,Toast.LENGTH_LONG).show();

    }


}
