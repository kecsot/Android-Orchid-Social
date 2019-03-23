package com.kecsot.orchidsocial.rest

import com.kecsot.orchidsocial.models.CommentDTO
import com.kecsot.orchidsocial.models.PaginationDTO
import com.kecsot.orchidsocial.models.PostDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PostRemoteInterface {

    @GET("getPosts")
    fun getPosts(@Query("page") page: Int, @Query("limit") itemsPerPage: Int): Observable<PaginationDTO<PostDTO>>

    @GET("getPost/{id}")
    fun getPostById(@Path("id") postid: String): Observable<PostDTO>

    @GET("getPostCommentsByPostId")
    fun getPostCommentsByPostId(@Query("post_id") postid: String, @Query("limit") itemsPerPage: Int): Observable<PaginationDTO<CommentDTO>>

}
