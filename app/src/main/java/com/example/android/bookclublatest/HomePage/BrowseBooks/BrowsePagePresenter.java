package com.example.android.bookclublatest.HomePage.BrowseBooks;

import android.support.annotation.NonNull;

import com.example.android.bookclublatest.Base.BasePresenter;
import com.example.android.bookclublatest.HomePage.RequestBook.RequestModel;
import com.example.android.bookclublatest.Member.AddBook.AddBookModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BrowsePagePresenter<V extends BrowsePageContract.View> extends BasePresenter<V>
        implements BrowsePageContract.Presenter<V>
{

}
