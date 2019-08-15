package com.example.android.bookclublatest.Faq;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.bookclublatest.Base.MvpContract;
import com.example.android.bookclublatest.Faq.MVP.FAQActivity;
import com.example.android.bookclublatest.Faq.Model.FAQModel;
import com.example.android.bookclublatest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddFaqActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.edt_txt_add_faq)
    EditText faq;
    @BindView(R.id.edt_txt_add_faq_answer)
     EditText answer;
    String mfaq,mans;
    @BindView(R.id.btn_add_faq)
    Button add;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faq);
        ButterKnife.bind(this);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("FAQ");

        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==add)
        {
            mfaq=faq.getText().toString().trim();
            mans=answer.getText().toString().trim();

            if(TextUtils.isEmpty(mfaq))
            {
                faq.setError("can't be blank");
                faq.requestFocus();
            }else if(TextUtils.isEmpty(mans))
            {
                answer.setError("can't be blank");
                answer.requestFocus();
            }else
            {
              String id=databaseReference.push().getKey();
                FAQModel faqModel=new FAQModel(mfaq,mans);
                databaseReference.child(id).setValue(faqModel);
                Toast.makeText(getApplicationContext(),"Uploaded",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(), FAQActivity.class);
                finish();
                startActivity(i);
            }

        }
    }
}
