package com.kecsot.orchidsocial.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kecsot.orchidsocial.R
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by kecsotamas on 2018. 03. 24..
 */

object GlideUtil {

    fun loadProfileImage(context: Context, url: String?, imageView: ImageView) {

        val requestOptions = RequestOptions()
                .error(R.drawable.ic_user_placeholder_64dp) // Todo: placeholder
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerInside()
                .circleCrop()

        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(imageView)

    }

}
