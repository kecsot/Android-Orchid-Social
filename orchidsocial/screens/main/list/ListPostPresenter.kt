package com.kecsot.orchidsocial.screens.main.list

import com.kecsot.orchidsocial.MyApplication
import com.kecsot.orchidsocial.base.pagination.AbstractPaginationBasePresenter
import com.kecsot.orchidsocial.models.PaginationDTO
import com.kecsot.orchidsocial.models.PostDTO
import com.kecsot.orchidsocial.repositories.PostRepository
import io.reactivex.Observable
import javax.inject.Inject


class ListPostPresenter(val itemsPerPage: Int) : AbstractPaginationBasePresenter<PostDTO, ListPostFragment>(), ListPostInterface.Presenter {

    @Inject
    lateinit var postRepository: PostRepository

    init {
        MyApplication.instance.repositoryComponent.inject(this)
    }

    override fun onViewResume() {

    }

    override fun getPaginationObservable(page: Int): Observable<PaginationDTO<PostDTO>>? {
        return postRepository.getPostList(page, itemsPerPage)
    }

}
