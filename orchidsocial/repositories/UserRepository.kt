package com.kecsot.orchidsocial.repositories

import com.kecsot.orchidsocial.models.UserDTO
import io.reactivex.Observable


class UserRepository : UserRepositoryInterface {


    override fun getUserDetailsByUid(userId: String): Observable<UserDTO> {
        return Observable.create { subscriber ->


        }
    }

}
