package com.example.android.bookclublatest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.bookclublatest.Base.BaseActivity;
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
    }

    @Override
    public void showToast(String message) {

    }

}
