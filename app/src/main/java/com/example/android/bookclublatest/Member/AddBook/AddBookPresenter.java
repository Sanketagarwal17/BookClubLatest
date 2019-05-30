package com.example.android.bookclublatest.Member.AddBook;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.example.android.bookclublatest.Base.BasePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddBookPresenter<V extends AddBookContract.View> extends BasePresenter<V>
        implements AddBookContract.Presenter<V>
{
    Boolean isFilled=false;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference().child("Books_List");

    @Override
    public void submit(String author, String book, String hardsofy, final String isbn, final String ism, String publisher, final String tags)
    {
        final AddBookModel model=new AddBookModel(author,book,hardsofy,isbn,ism,publisher,tags,"not issued");
        databaseReference.child(isbn).child(hardsofy).child(ism).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    getMvpView().showToast("Successfully added in Database");
                else
                    getMvpView().showToast(task.getException().getMessage());
            }
        });

    }
}
