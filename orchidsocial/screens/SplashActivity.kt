package com.kecsot.orchidsocial.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.kecsot.orchidsocial.R
import com.kecsot.orchidsocial.screens.login.LoginActivity


class SplashActivity : Activity() {

    private val SPLASH_DISPLAY_LENGTH = 50L

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.splash_activity)

        Handler().postDelayed({
            val mainIntent = Intent(this@SplashActivity, LoginActivity::class.java)
            this@SplashActivity.startActivity(mainIntent)
            this@SplashActivity.finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}