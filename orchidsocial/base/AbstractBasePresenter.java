package com.kecsot.orchidsocial.base;


import com.kecsot.orchidsocial.base.interfaces.BasePresenterInterface;

import io.reactivex.disposables.CompositeDisposable;

public abstract class AbstractBasePresenter<V extends AbstractBaseFragment> implements BasePresenterInterface {

    protected CompositeDisposable compositeDisposable;
    protected V view;

    public AbstractBasePresenter() {
        compositeDisposable = new CompositeDisposable();
    }

    protected void setView(V view) {
        this.view = view;
    }

    @Override
    public void onViewRestart() {
    }

    @Override
    public void onViewDestroy() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}