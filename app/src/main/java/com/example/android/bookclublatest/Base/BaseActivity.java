package com.example.android.bookclublatest.Base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public abstract class BaseActivity extends AppCompatActivity
        implements MvpContract.View {


    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showLoading() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        //  Timber.e(message);
    }


    @Override
    public void hideKeyboard() { }

}













//Infuture for implementing Dagger


/*    //ActivityComponent mActivityComponent;

    //private Unbinder mUnBinder;





        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this)).adapterModule(new AdapterModule(this))
                .applicationComponent(((AppController) getApplication()).getComponent())
                .build();
    }

    public void onAttach(Context c) {
        progressDialog = new ProgressDialog(c);
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }



}
*/
