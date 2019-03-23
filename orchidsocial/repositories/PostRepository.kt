package com.kecsot.orchidsocial.repositories

import com.kecsot.orchidsocial.MyApplication
import com.kecsot.orchidsocial.models.CommentDTO
import com.kecsot.orchidsocial.models.PaginationDTO
import com.kecsot.orchidsocial.models.PostDTO
import com.kecsot.orchidsocial.rest.PostRemoteInterface
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class PostRepository : PostRepositoryInterface {

    @Inject
    lateinit var postRemoteInterface: PostRemoteInterface

    init {
        MyApplication.instance.remoteServiceComponent.inject(this)
    }

    override fun getPostById(postId: String): Observable<PostDTO> {
        return postRemoteInterface.getPostById(postId)
    }

    override fun getPostList(pageIndex: Int, itemsPerPage: Int): Observable<PaginationDTO<PostDTO>> {
        return postRemoteInterface.getPosts(pageIndex, itemsPerPage)
    }

    override fun getPostCommentsById(postId: String, itemsPerPage: Int): Observable<PaginationDTO<CommentDTO>> {
        return postRemoteInterface.getPostCommentsByPostId(postId, itemsPerPage)
    }

    override fun addCommentToPost(postId: String, comment: CommentDTO): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}