package com.kecsot.orchidsocial.screens.post

import com.kecsot.orchidsocial.models.PostDTO

interface PostInterface {

    interface View {
        fun onPostLoadingStarted()
        fun errorOnPostLoading();
        fun loadPost(postInformationModel: PostDTO);
    }

    interface Presenter {
        fun loadPostByPostId(postId: String)
    }

}