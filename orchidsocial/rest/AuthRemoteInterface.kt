package com.kecsot.orchidsocial.rest

import com.kecsot.orchidsocial.models.LoginDTO
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query


interface AuthRemoteInterface {

    @POST("authenticate/facebook")
    fun loginWithFacebook(@Query("facebook_userid") userId: String, @Query("facebook_token") facebookToken: String): Observable<LoginDTO>

}
