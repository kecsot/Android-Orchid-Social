package com.kecsot.orchidsocial.repositories

import com.kecsot.orchidsocial.models.LoginDTO
import com.kecsot.orchidsocial.models.UserDTO
import io.reactivex.Observable


interface AuthRepositoryInterface {

    fun loginWithFacebook(userId: String, facebookToken: String): Observable<LoginDTO>

}