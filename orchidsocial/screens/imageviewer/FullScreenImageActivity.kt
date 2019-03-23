package com.kecsot.orchidsocial.screens.imageviewer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.crashlytics.android.Crashlytics
import com.kecsot.orchidsocial.R
import kotlinx.android.synthetic.main.image_fullscreen_activity.*


class FullScreenImageActivity : AppCompatActivity() {

    companion object {
        val INTENT_URL: String = "FullScreenImage:IntentUrl"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_fullscreen_activity)

        val url = intent?.extras?.getString(INTENT_URL)

        url?.let {

            val requestOptions = RequestOptions()
                    .error(R.drawable.error)            // TODO
                    .diskCacheStrategy(DiskCacheStrategy.ALL)

            Glide.with(this)
                    .asBitmap()
                    .load(it)
                    .apply(requestOptions)
                    .into(touchImageView)

        } ?: run {
            onErrorHappened()
        }

    }

    fun onErrorHappened() {
        Crashlytics.logException(Exception("Error FullScreenImageActivity can't open. Extras: ${intent.extras.toString()}"))
        finish()
    }

}
