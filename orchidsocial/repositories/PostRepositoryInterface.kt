package com.kecsot.orchidsocial.repositories

import com.kecsot.orchidsocial.models.CommentDTO
import com.kecsot.orchidsocial.models.PaginationDTO
import com.kecsot.orchidsocial.models.PostDTO
import io.reactivex.Completable
import io.reactivex.Observable

interface PostRepositoryInterface {

    fun getPostById(postId: String): Observable<PostDTO>

    fun getPostList(pageIndex: Int, itemsPerPage: Int): Observable<PaginationDTO<PostDTO>>

    fun getPostCommentsById(postId: String, itemsPerPage: Int): Observable<PaginationDTO<CommentDTO>>

    fun addCommentToPost(postId: String, comment: CommentDTO): Completable


}