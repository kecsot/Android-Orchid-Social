package com.kecsot.orchidsocial.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.kecsot.orchidsocial.base.interfaces.BaseViewInterface;


public abstract class AbstractBaseFragment<P extends AbstractBasePresenter> extends Fragment implements BaseViewInterface<P> {

    public P presenter;

    public AbstractBaseFragment(P presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayoutId(), container, false);

        beforeOnCreateViewFinished();
        beforeOnCreateViewFinished(view);
        hideSoftKeyboard();
        hideSoftInputFromView();
        return view;
    }

    protected abstract int getFragmentLayoutId();

    protected void beforeOnCreateViewFinished(){
    }

    public void beforeOnCreateViewFinished(View view) {
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onViewResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onViewDestroy();
    }

    @Override
    public void setPresenter(P presenter) {
        if (presenter == null) {
            throw new RuntimeException("Presenter cannot be null");
        }
        this.presenter = presenter;
    }

    protected void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    protected void setTitle(String title) {
        if (getActivity() != null) {
            getActivity().setTitle(title);
        }
    }

    protected void hideSoftKeyboard() {
        if (getActivity() != null) {
            getActivity().getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
            );
        }
    }

    protected void hideSoftInputFromView() {
        if (getActivity() != null) {
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
    }
}
