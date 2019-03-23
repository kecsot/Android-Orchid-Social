package com.kecsot.orchidsocial.screens.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.kecsot.orchidsocial.MyApplication
import com.kecsot.orchidsocial.R
import com.kecsot.orchidsocial.screens.main.MainActivity
import kotlinx.android.synthetic.main.login_fragment.*
import java.util.*
import javax.inject.Inject


class LoginActivity : AppCompatActivity(), LoginInterface.View {

    @Inject
    lateinit var loginManager: OrchidLoginManager;

    init {
        MyApplication.instance.managerComponent.inject(this)
    }

    private var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_fragment)
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this)

        loginProgressBar.visibility = View.GONE
        setLoginButtonsVisibility(View.VISIBLE)

        loginManager.setViewInterface(this)
       //TODO  setFacebookLogin()
       onLoginSuccess()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setFacebookLogin() {
        val isFbLoggedIn = loginManager.isFacebookLoggedIn()

        if (isFbLoggedIn) {
            loginManager.onFacebookLogin();
        } else {
            callbackManager = CallbackManager.Factory.create();
            facebookLoginButton.setReadPermissions(Arrays.asList("email"));
            facebookLoginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    loginManager.onFacebookLogin();
                }

                override fun onCancel() {
                    onLoginFailed()
                }

                override fun onError(exception: FacebookException) {
                    onLoginFailed()
                }
            })
        }
    }

    override fun onLoginStarted() {
        setLoginButtonsVisibility(View.GONE)
        loginProgressBar.visibility = View.VISIBLE
    }

    override fun onLoginSuccess() {
        loginProgressBar.visibility = View.GONE
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onLoginFailed() {
        setLoginButtonsVisibility(View.VISIBLE)
        loginProgressBar.visibility = View.GONE

        LoginManager.getInstance().logOut(); // Fb Logout
    }

    override fun setLoginButtonsVisibility(visibility: Int) {
        facebookLoginButton.visibility = visibility
    }


}
