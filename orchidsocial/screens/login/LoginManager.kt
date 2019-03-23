package com.kecsot.orchidsocial.screens.login

import android.util.Log
import com.crashlytics.android.Crashlytics
import com.facebook.AccessToken
import com.kecsot.orchidsocial.MyApplication
import com.kecsot.orchidsocial.repositories.AuthRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OrchidLoginManager : LoginInterface.Manager {

    @Inject
    lateinit var authRepository: AuthRepository;

    private var viewInterface: LoginInterface.View? = null

    init {
        MyApplication.instance.repositoryComponent.inject(this)
    }

    fun setViewInterface(viewInterface: LoginInterface.View) {
        this.viewInterface = viewInterface
    }

    override fun onFacebookLogin() {
        viewInterface?.onLoginStarted()
        val accessToken = AccessToken.getCurrentAccessToken()

        accessToken?.let {
            authRepository.loginWithFacebook(it.userId, it.token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                // save token, refresh stb
                                Log.d("TOKEN","Token: " + it.token)
                                viewInterface?.onLoginSuccess()
                            },
                            {
                                Crashlytics.logException(it)
                                viewInterface?.onLoginFailed()
                            }
                    )
        } ?: kotlin.run {
            viewInterface?.onLoginFailed()
        }
    }

    override fun isFacebookLoggedIn(): Boolean {
        return AccessToken.getCurrentAccessToken() != null
    }

}
