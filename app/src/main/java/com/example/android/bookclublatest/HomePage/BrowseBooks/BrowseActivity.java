package com.example.android.bookclublatest.HomePage.BrowseBooks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.Member.AddBook.AddBookModel;
import com.example.android.bookclublatest.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrowseActivity extends BaseActivity implements BrowsePageContract.View {

    @BindView(R.id.browse_recyclerview)
    RecyclerView recyclerView;

    BrowseModel browseModel;
    ArrayList<BrowseModel> arrayList;
    String name="",author="",publisher="",hardsets="",softsets="";
    BrowseAdapter browseAdapter;

    BrowsePageContract.Presenter<BrowsePageContract.View> presenter;

    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference("Books_List");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_books);
        presenter=new BrowsePagePresenter<BrowsePageContract.View>();
        ButterKnife.bind(this);

        arrayList=new ArrayList<>();
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        browseAdapter=new BrowseAdapter(this,arrayList);
        recyclerView.setAdapter(browseAdapter);

        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                arrayList.clear();
                for(DataSnapshot ds1:dataSnapshot.getChildren())
                {
                    hardsets="0";
                    softsets="0";
                    for(DataSnapshot ds2:ds1.getChildren())
                    {
                        if(ds2.getKey().equals("Hard Copy")) {
                            int counter=0;
                            for (DataSnapshot ds3 : ds2.getChildren()) {
                                counter++;
                                AddBookModel model= ds3.getValue(AddBookModel.class);
                                name=model.getBook();
                                author=model.getAuthor();
                                publisher=model.getPublisher();
                            }
                            hardsets=""+counter;
                        }
                        else
                        {
                            int counter=0;
                            for (DataSnapshot ds3 : ds2.getChildren()) {
                                counter++;
                                AddBookModel model= ds3.getValue(AddBookModel.class);
                                name=model.getBook();
                                author=model.getAuthor();
                                publisher=model.getPublisher();
                            }
                            softsets=""+counter;
                        }
                    }
                    browseModel=new BrowseModel(name,author,publisher,hardsets,softsets);
                    arrayList.add(browseModel);
                }
                browseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
    @Override
    public void showToast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
