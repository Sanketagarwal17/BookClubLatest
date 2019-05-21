package com.example.android.bookclublatest;

import com.example.android.bookclublatest.Base.MvpContract;
import com.example.android.bookclublatest.Member.AddBook.AddBookContract;

public class IssueBookContract {

    public interface View extends MvpContract.View {

        void showToast(String message);
    }


    public interface Presenter<V extends IssueBookContract.View> extends MvpContract.Presenter<V> {

    }
}
