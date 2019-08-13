package com.example.android.bookclublatest.Faq.MVP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.android.bookclublatest.Base.MvpContract;
import com.example.android.bookclublatest.Faq.AddFaqActivity;
import com.example.android.bookclublatest.Faq.Model.FAQModel;
import com.example.android.bookclublatest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.faq_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.button2)
    Button addfaq;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    FAQAdapter adapter;
    ArrayList<FAQModel> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("FAQ");
        addfaq.setOnClickListener(this);
        //getjson();
        loaddata();
    }


    void getjson() {
        String json;

        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("faq.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer, "utf-8");
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    String question = object.getString("question");
                    String answer = object.getString("answer");
                    arrayList.add(new FAQModel(question, answer));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        adapter = new FAQAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);
    }

    public  void loaddata()
    {  arrayList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                   String ques=ds.child("question").getValue(String.class);
                   String ans=ds.child("content").getValue(String.class);
                   FAQModel faqModel=new FAQModel(ques,ans);
                   arrayList.add(faqModel);
                }
                adapter = new FAQAdapter(arrayList, getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onClick(View v) {
       if(v==addfaq)
        {
            Intent i=new Intent(getApplicationContext(), AddFaqActivity.class);
            startActivity(i);
        }
    }
}