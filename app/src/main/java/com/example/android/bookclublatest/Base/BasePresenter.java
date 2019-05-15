package com.example.android.bookclublatest.Base;

import android.util.Log;

public class BasePresenter<V extends MvpContract.View> implements MvpContract.Presenter<V>{

    private static final String TAG = "BasePresenter";
    private V mvpView;
    @Override
    public void onAttach(V mvpView) {
        if(mvpView == null)
        {
            Log.d(TAG, "onAttach: ");
        }
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mvpView = null;
    }

    public V getMvpView()
    {
        return mvpView;
    }
}
