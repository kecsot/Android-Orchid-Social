package com.kecsot.orchidsocial.screens.post.comment

import com.crashlytics.android.Crashlytics
import com.kecsot.orchidsocial.MyApplication
import com.kecsot.orchidsocial.base.pagination.AbstractPaginationBasePresenter
import com.kecsot.orchidsocial.models.CommentDTO
import com.kecsot.orchidsocial.models.PaginationDTO
import com.kecsot.orchidsocial.repositories.PostRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CommentPresenter(val itemsPerPage: Int) : AbstractPaginationBasePresenter<CommentDTO, CommentFragment>(), CommentInterface.Presenter {

    @Inject
    lateinit var postRepository: PostRepository

    init {
        MyApplication.instance.repositoryComponent.inject(this)
    }


    override fun getPaginationObservable(page: Int): Observable<PaginationDTO<CommentDTO>>? {
        return postRepository.getPostCommentsById(view.getPostId(), itemsPerPage)
    }


    fun addCommentToPost(postId: String, comment: CommentDTO) {
        val call = postRepository.addCommentToPost(postId, comment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view.successOnAddComment()
                        },
                        {
                            Crashlytics.logException(it)
                            view.errorOnAddComment()
                        }
                )
        compositeDisposable.add(call)
    }

    override fun onViewResume() {

    }
}

