package com.kecsot.orchidsocial.screens.post

import com.crashlytics.android.Crashlytics
import com.kecsot.orchidsocial.MyApplication
import com.kecsot.orchidsocial.base.AbstractBasePresenter
import com.kecsot.orchidsocial.repositories.PostRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PostPresenter : AbstractBasePresenter<PostFragment>(), PostInterface.Presenter {

    @Inject
    lateinit var postRepository: PostRepository

    init {
        MyApplication.instance.repositoryComponent.inject(this)
    }

    override fun loadPostByPostId(postId: String) {

        val call = postRepository.getPostById(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view.loadPost(it)
                        },
                        {
                            Crashlytics.logException(it)
                            view.errorOnPostLoading()
                        }
                )
        compositeDisposable.add(call)
    }

    override fun onViewResume() {}
}

