package com.example.android.bookclublatest.Authentication.SignUp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.bookclublatest.Admin.RequestsForMember.AdminActivity.AdminActivity;
import com.example.android.bookclublatest.Authentication.Login.LoginActivity;
import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.BuildConfig;
import com.example.android.bookclublatest.HomePage.HomePageActivity;
import com.example.android.bookclublatest.Member.MemberActivity;
import com.example.android.bookclublatest.R;
import com.example.android.bookclublatest.SharedPref.SharedPref;
import com.example.android.bookclublatest.Student.StudentActivity;
import com.firebase.ui.auth.ui.email.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends BaseActivity implements SignUpContract.View
{
    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.admissionnumber)
    EditText admissionnumber;

    @BindView(R.id.phonenumber)
    EditText phonenumber;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.create_account)
    Button createaccount;

    @BindView(R.id.add_profilepic_iv)
    CircularImageView add_profile_pic;

    SignUpContract.Presenter<SignUpContract.View> mPresenter;
    FirebaseStorage firebasestorage= FirebaseStorage.getInstance();
    StorageReference storageReference=firebasestorage.getReference().child("bookClub/"+System.currentTimeMillis()+".jpg");
    int capture = 10;

    ProgressDialog progressDialog;

    String doc_url="";
    Uri doc_data=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        mPresenter = new SignUpPresenter<>();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading");

        ((SignUpPresenter<SignUpContract.View>) mPresenter).onAttach(this);

        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(email.getText().toString().contains("iitism.ac.in") || email.getText().toString().contains("ism.ac.in"))) {
                    email.setError("Please enter College ID");
                    email.requestFocus();
                }
                else if(password.getText().toString().isEmpty())
                {
                    password.setError("Please Enter Password");
                    password.requestFocus();
                }
                else
                {
                    mPresenter.CreateAccount(name.getText().toString().trim(), email.getText().toString().trim()
                            , admissionnumber.getText().toString().trim(), phonenumber.getText().toString().trim()
                            , password.getText().toString().trim(), doc_url);

                    String email1 = email.getText().toString().trim();
                    String email2 = email1.replace('.', ',');

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

                    databaseReference.child("Status").child(email2).setValue("Student").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }
            }
        });

        add_profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,capture);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode==capture)&&(resultCode==RESULT_OK)&&(data!=null)&&(data.getData()!=null))
        {
            progressDialog.show();
            doc_data=data.getData();
            storageReference.putFile(doc_data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri data=uri;
                            doc_url = data.toString();
                        }
                    });
                }
            });
            progressDialog.dismiss();
            add_profile_pic.setImageURI(doc_data);
        }
    }

    private void startActivityUtil(Class activity)
    {
        SharedPref sharedPref=new SharedPref(this);
        sharedPref.setUsername(name.getText().toString());
        sharedPref.setEmail(email.getText().toString());
        sharedPref.setMobile(phonenumber.getText().toString());
        sharedPref.setAdmission(admissionnumber.getText().toString());
        sharedPref.setAccessLevel("Student");
        sharedPref.setImageUrl(doc_url);
        Intent intent = new Intent(SignUpActivity.this, activity);
        startActivity(intent);
        finish();
    }


    @Override
    public void showSignUpResult() {


        if(BuildConfig.FLAVOR.equals("admin"))
        { startActivityUtil(AdminActivity.class); }

        else if(BuildConfig.FLAVOR.equals("member"))
        { startActivityUtil(MemberActivity.class); }

        else
        { startActivityUtil(HomePageActivity.class); }
    }

    @Override
    public void erroronLoading(String error) {
        Toast.makeText(SignUpActivity.this,"OOPS Something Wrong Happen"+error,Toast.LENGTH_LONG).show();


    }

    @Override
    public void showToast(String url) {
        Log.i("Url",url);
    }
}
