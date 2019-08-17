package com.example.android.bookclublatest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.HomePage.BrowseBooks.BrowseModel;
import com.example.android.bookclublatest.HomePage.HomePageActivity;
import com.example.android.bookclublatest.HomePage.RequestBook.RequestActivity;
import com.example.android.bookclublatest.Member.AddBook.AddBookModel;
import com.example.android.bookclublatest.Member.AddBook.AddBookModel1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IssueBook extends BaseActivity implements IssueBookContract.View {

    private static final String TAG = "IssueBook";
    @BindView(R.id.rv_issue_book)
    RecyclerView recyclerView;

    IssueBookContract.Presenter<IssueBookContract.View> presenter;
    IssueBookAdapter issueBookAdapter;
    ArrayList<AddBookModel1> arrayList;
    private DatabaseReference bookRef;
    @BindView(R.id.searchbox)
    EditText searchBox;
    @BindView(R.id.imageView7)
    ImageView home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_book);
        ButterKnife.bind(this);
        presenter = new IssueBookPresenter<>();
        presenter.onAttach(this);
        arrayList = new ArrayList<>();
        issueBookAdapter = new IssueBookAdapter(this,arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(issueBookAdapter);
        bookRef = FirebaseDatabase.getInstance().getReference().child("Books_List");
        bookRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot d1 : dataSnapshot.getChildren())
                {
                    AddBookModel addBookModel = null;
                    int count =0;
                    for(DataSnapshot d2 : d1.getChildren())
                    {
                        for(DataSnapshot d3 : d2.getChildren())
                        {
                             addBookModel = d3.getValue(AddBookModel.class);
                             count++;
                        }
                    }
                    AddBookModel1 addBookModel1 = new AddBookModel1(addBookModel.getBook()
                    ,addBookModel.getAuthor(),addBookModel.getPublisher(),addBookModel.getTags()
                    ,addBookModel.getHardsofy(),addBookModel.getIsm(),addBookModel.getIsbn()
                    ,addBookModel.getStatus(),String.valueOf(count),addBookModel.getUrl(),addBookModel.getDesc());
                    Log.d(TAG, "onDataChange: " + count);
                    arrayList.add(addBookModel1);

                }
                Log.d(TAG, "onDataChange: " + arrayList.size());

                issueBookAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void filter( final String s) {
        FirebaseDatabase.getInstance().getReference().child("Books_List").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                arrayList.clear();
                for(DataSnapshot d1 : dataSnapshot.getChildren()) {
                    AddBookModel addBookModel = null;
                    int count = 0;
                    for (DataSnapshot d2 : d1.getChildren()) {
                        for (DataSnapshot d3 : d2.getChildren()) {
                            addBookModel = d3.getValue(AddBookModel.class);

                            count++;
                        }
                    }
                    if (addBookModel.getBook().contains(s) || addBookModel.getPublisher().contains(s) || addBookModel.getAuthor().contains(s))
                    {
                        AddBookModel1 addBookModel1 = new AddBookModel1(addBookModel.getBook()
                                , addBookModel.getAuthor(), addBookModel.getPublisher(), addBookModel.getTags()
                                , addBookModel.getHardsofy(), addBookModel.getIsm(), addBookModel.getIsbn()
                                , addBookModel.getStatus(), String.valueOf(count), addBookModel.getUrl(), addBookModel.getDesc());
                    arrayList.add(addBookModel1);
                }
                }
                Log.d(TAG, "onDataChange: " + arrayList.size());


                issueBookAdapter.filterList(arrayList);
                issueBookAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

    @Override
    public void showToast(String message) {

    }

}
