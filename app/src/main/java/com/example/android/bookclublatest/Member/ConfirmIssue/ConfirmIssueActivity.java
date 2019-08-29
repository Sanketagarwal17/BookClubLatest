package com.example.android.bookclublatest.Member.ConfirmIssue;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.android.bookclublatest.Member.ConfirmIssue.ConfirmProceed.ProceedActivity;
import com.example.android.bookclublatest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmIssueActivity extends AppCompatActivity implements ConfirmIssueAdapter.Clicklistener {

    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference("Issue Requests");

    @BindView(R.id.issues_bar)
    ProgressBar progressBar;
    @BindView(R.id.issues_recycler)
    RecyclerView recyclerView;

    List<ConfirmIssueModel> list=new ArrayList<>();
    ConfirmIssueAdapter adapter;

    String email,name,isbn,ism,status,time,url;

    TextView title;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_issue);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        title = (TextView) findViewById(R.id.textView26);
        title.setText("ISSUE REQUESTS");

        FloatingActionButton fab=findViewById(R.id.btn_sort);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSortDialog();
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                progressBar.setVisibility(View.VISIBLE);
                list.clear();
                for(DataSnapshot ds1:dataSnapshot.getChildren())
                {
                    email=ds1.getKey();
                    for(DataSnapshot ds2:ds1.getChildren())
                    {
                        status=ds2.child("Status").getValue().toString();
                        if(status.equals("pending"))
                        {
                            name=ds2.child("Name").getValue().toString();
                            time=ds2.child("Timestamp").getValue().toString();
                            isbn=ds2.child("ISBN").getValue().toString();
                            ism=ds2.child("ISM").getValue().toString();
                            url = ds2.child("url").getValue().toString();
                            list.add(new ConfirmIssueModel(email,isbn,name,time,ism,url));
                        }
                    }
                }
                progressBar.setVisibility(View.GONE);
                adapter=new ConfirmIssueAdapter(list,ConfirmIssueActivity.this,ConfirmIssueActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sort,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id=item.getItemId();
       if(id==R.id.sort_menu)
       {
           showSortDialog();
           return  true;
       }
        return super.onOptionsItemSelected(item);
    }

    private void showSortDialog() {
        String[] options={"By Name","By Time"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Sort By");
        builder.setIcon(R.drawable.ic_sort);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0)
                {
                    String a="Name";
                    ShowData(a);
                }
                if(which==1)
                {
                    String a="Time";
                    ShowData(a);
                }
            }
        });
        builder.create().show();
    }

    public  void ShowData(String s)
    {
        if (s.equals("Name"))
       {
        Collections.sort(list,ConfirmIssueModel.By_Name);
       }
        else if (s.equals("Time"))
        {
            Collections.sort(list,ConfirmIssueModel.By_Time);
        }
        adapter=new ConfirmIssueAdapter(list,ConfirmIssueActivity.this,ConfirmIssueActivity.this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void proceed(int pos)
    {
        Intent intent=new Intent(this, ProceedActivity.class);
        intent.putExtra("Email",list.get(pos).getEmail());
        intent.putExtra("Book",list.get(pos).getName());
        intent.putExtra("isbn",list.get(pos).getIsbn());
        intent.putExtra("ism",list.get(pos).getIsm());
        intent.putExtra("url",list.get(pos).getUrl());
        finish();
        startActivity(intent);
    }
}
