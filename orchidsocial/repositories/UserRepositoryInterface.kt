package com.kecsot.orchidsocial.repositories

import com.kecsot.orchidsocial.models.UserDTO
import io.reactivex.Observable


interface UserRepositoryInterface {

    fun getUserDetailsByUid(userId: String): Observable<UserDTO>

}