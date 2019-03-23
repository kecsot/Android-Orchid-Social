package com.kecsot.orchidsocial.repositories

import com.kecsot.orchidsocial.MyApplication
import com.kecsot.orchidsocial.models.LoginDTO
import com.kecsot.orchidsocial.rest.AuthRemoteInterface
import io.reactivex.Observable
import javax.inject.Inject

// TODO: G+, Simple Login,Reg,ForgotPw
class AuthRepository : AuthRepositoryInterface {

    @Inject
    lateinit var authRemoteInterface: AuthRemoteInterface

    init {
        MyApplication.instance.remoteServiceComponent.inject(this)
    }

    override fun loginWithFacebook(userId: String, facebookToken: String): Observable<LoginDTO> {
        return authRemoteInterface.loginWithFacebook(userId, facebookToken)
    }

}
