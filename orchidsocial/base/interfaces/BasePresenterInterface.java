package com.kecsot.orchidsocial.base.interfaces;


public interface BasePresenterInterface<T> {

    void onViewResume();

    void onViewRestart();

    void onViewDestroy();

}