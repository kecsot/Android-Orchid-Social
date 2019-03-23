package com.kecsot.orchidsocial.dagger.components

import com.kecsot.orchidsocial.dagger.modules.RepositoryModule
import com.kecsot.orchidsocial.screens.login.OrchidLoginManager
import com.kecsot.orchidsocial.screens.main.list.ListPostPresenter
import com.kecsot.orchidsocial.screens.post.PostActivity
import com.kecsot.orchidsocial.screens.post.PostPresenter
import com.kecsot.orchidsocial.screens.post.adapters.CommentAdapter
import com.kecsot.orchidsocial.screens.post.comment.CommentPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by kecsotamas on 2018. 03. 10..
 */
@Singleton
@Component(modules = arrayOf(RepositoryModule::class))
interface RepositoryComponent {

    fun inject(activity: PostActivity)

    fun inject(activity: PostPresenter)

    fun inject(view: CommentAdapter)

    fun inject(commentPresenter: CommentPresenter)

    fun inject(listPostPresenter: ListPostPresenter)

    fun inject(orchidLoginManager: OrchidLoginManager)

}