package com.example.android.bookclublatest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.android.bookclublatest.Base.BaseActivity;
import com.example.android.bookclublatest.HomePage.BrowseBooks.BrowseModel;
import com.example.android.bookclublatest.Member.AddBook.AddBookModel;
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
    ArrayList<AddBookModel> arrayList;
    private DatabaseReference bookRef;
    @BindView(R.id.searchbox)
    EditText searchBox;



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
                    for(DataSnapshot d2 : d1.getChildren())
                    {
                        for(DataSnapshot d3 : d2.getChildren())
                        {
                            AddBookModel addBookModel = d3.getValue(AddBookModel.class);
                            arrayList.add(addBookModel);
                        }
                    }
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
    }

    private void filter( final String s) {
        FirebaseDatabase.getInstance().getReference().child("Books_List").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                ArrayList<AddBookModel> arrayList1 = new ArrayList<>();
                for(DataSnapshot ds1:dataSnapshot.getChildren())
                {
                    for(DataSnapshot ds2:ds1.getChildren())
                    {
                        if(ds2.getKey().equals("Hard Copy")) {
                            for (DataSnapshot ds3 : ds2.getChildren()) {
                                AddBookModel model= ds3.getValue(AddBookModel.class);
                                if((model.getBook().toLowerCase().contains(s.toLowerCase()))||
                                        (model.getAuthor().toLowerCase().contains(s.toLowerCase()))||
                                        (model.getPublisher().toLowerCase().contains(s.toLowerCase())))
                                arrayList1.add(model);
                            }
                        }
                        else
                        {
                            for (DataSnapshot ds3 : ds2.getChildren()) {
                                AddBookModel model= ds3.getValue(AddBookModel.class);
                                if((model.getBook().toLowerCase().contains(s.toLowerCase()))||
                                        (model.getAuthor().toLowerCase().contains(s.toLowerCase()))||
                                        (model.getPublisher().toLowerCase().contains(s.toLowerCase())))
                                    arrayList1.add(model);
                            }
                        }
                    }
                }
                issueBookAdapter.filterList(arrayList1);

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

/**
 * this is test to show add ,
 * djgd
 * **/