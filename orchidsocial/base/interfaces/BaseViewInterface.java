package com.kecsot.orchidsocial.base.interfaces;


public interface BaseViewInterface<T extends BasePresenterInterface> {

    void setPresenter(T presenter);

}