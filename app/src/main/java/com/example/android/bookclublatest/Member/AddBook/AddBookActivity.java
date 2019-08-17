package com.example.android.bookclublatest.Member.AddBook;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookclublatest.Authentication.Login.LoginActivity;
import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddBookActivity extends BaseActivity implements AddBookContract.View {

    private static final String TAG = "AddBookActivity";
    @BindView(R.id.add_bookname)
    EditText name;
    @BindView(R.id.add_description)
    EditText desc;
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
    @BindView(R.id.add_book_image)
    Button addbookimage;

    @BindView(R.id.return_home)
    ImageView home;
    @BindView(R.id.textView26)
    TextView title;

    FirebaseStorage firebasestorage= FirebaseStorage.getInstance();
    StorageReference storageReference=firebasestorage.getReference().child("Books/"+System.currentTimeMillis()+".jpg");
    int capture = 10;
    ProgressDialog progressDialog;

    String doc_url="";
    Uri doc_data=null;
    private int check=0;
    String link="null";
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
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading");
        intentIntegrator=new IntentIntegrator(this);
       // intentIntegrator.setCaptureActivity(CaptureActivityPortrait.class);
        ISBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Animation sgAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
                //ISBN.startAnimation(sgAnimation);
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
        addbookimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,capture);


            }
        });

        title.setText("Add Book");
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
                if(link.equals("null")){
                    Toast.makeText(AddBookActivity.this, "Please Upload Photo", Toast.LENGTH_SHORT).show();
                }
                else {
                    presenter.submit(mname, mauthor, mpublication, misbn, mism, mtags, mchecbox, link,desc.getText().toString());
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode==capture)&&(resultCode==RESULT_OK)&&(data!=null)&&(data.getData()!=null))
        {
            Toast.makeText(this, "Uploading ...", Toast.LENGTH_SHORT).show();
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
                            link=doc_url;
                            Toast.makeText(AddBookActivity.this, "Photo added Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            progressDialog.dismiss();

        }

    }


    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



}
