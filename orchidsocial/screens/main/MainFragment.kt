package com.kecsot.orchidsocial.screens.main

import android.util.Log
import com.kecsot.orchidsocial.R
import com.kecsot.orchidsocial.base.AbstractBaseFragment

/**
 * Created by kecsotamas on 2018. 03. 15..
 */
class MainFragment : AbstractBaseFragment<MainPresenter>(MainPresenter()) {


    override fun getFragmentLayoutId(): Int {
        return R.layout.main_fragment
    }

    override fun beforeOnCreateViewFinished() {
       //(activity as MainActivity).changeFragment(MainFragment2())
        Log.d("Fragment","Fragment created")
    }

}