package com.kecsot.orchidsocial.screens.login


interface LoginInterface {

    interface View {
        fun onLoginStarted()
        fun onLoginSuccess()
        fun onLoginFailed()
        fun setLoginButtonsVisibility(visibility: Int)
    }

    interface Manager {
        fun isFacebookLoggedIn(): Boolean
        fun onFacebookLogin()
    }

}