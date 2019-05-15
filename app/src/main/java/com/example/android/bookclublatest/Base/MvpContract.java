package com.example.android.bookclublatest.Base;

public class MvpContract {


    public interface View {
        void showLoading();

        void hideLoading();

        void onError(String message);

        void hideKeyboard();
    }

    public interface Presenter<V extends View>
    {
        void onAttach(V mvpView);

        void onDetach();

    }

}
