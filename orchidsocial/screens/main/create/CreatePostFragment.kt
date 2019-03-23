package com.kecsot.orchidsocial.screens.main.create

import android.os.Bundle
import com.kecsot.orchidsocial.R
import com.kecsot.orchidsocial.base.AbstractBaseFragment

open class CreatePostFragment : AbstractBaseFragment<CreatePostPresenter>(CreatePostPresenter()), CreatePostInterface.View {


    override fun getFragmentLayoutId(): Int {
        return R.layout.create_post_fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

}