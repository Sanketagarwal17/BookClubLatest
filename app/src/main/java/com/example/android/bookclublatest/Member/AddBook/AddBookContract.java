package com.example.android.bookclublatest.Member.AddBook;

import com.example.android.bookclublatest.Base.MvpContract;

public class AddBookContract
{

    public interface View extends MvpContract.View {

        void showToast(String message);
    }


    public interface Presenter<V extends View> extends MvpContract.Presenter<V> {
        void submit(String name, String author, String publication,String isbn, String ism, String tags, String hardsoft,String url);
    }
}
