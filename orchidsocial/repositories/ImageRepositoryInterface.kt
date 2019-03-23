package com.kecsot.orchidsocial.repositories

import io.reactivex.Observable


interface ImageRepositoryInterface {

    fun uploadImage(filePath: String, ownerId: String): Observable<String>

}