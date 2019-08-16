package com.example.android.bookclublatest.Authentication.ChangePassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePasswordActivity extends BaseActivity implements ChangePasswordContract.View {

    @BindView(R.id.oldpass)
    EditText oldpassword;

    @BindView(R.id.newpass)
    EditText newpassword;

    @BindView(R.id.confirmnewpass)
    EditText confirmnewpassword;

    @BindView(R.id.change)
    Button change_password;

    ChangePasswordContract.Presenter<ChangePasswordContract.View> mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mPresenter=new ChangePasswordPresenter<ChangePasswordContract.View>();
        ((ChangePasswordPresenter<ChangePasswordContract.View>) mPresenter).onAttach(this);
        ButterKnife.bind(this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String email = user.getEmail();

        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirmnewpassword.getText().toString().equals(newpassword.getText().toString()))
                {  mPresenter.changePassword(email,oldpassword.getText().toString(),newpassword.getText().toString());

                }
                else {
                    confirmnewpassword.setError("password not match");
                }
            }
        });



    }



    @Override
    public void errorOnLoading(String error) {
        Toast.makeText(ChangePasswordActivity.this, error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void close() {
        finish();
    }
}
