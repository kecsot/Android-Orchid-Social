package com.kecsot.orchidsocial.screens.post.comment

import com.kecsot.orchidsocial.base.pagination.PaginationBaseInterface
import com.kecsot.orchidsocial.models.CommentDTO
import com.kecsot.orchidsocial.models.PostDTO

interface CommentInterface {

    interface View : PaginationBaseInterface.View<CommentDTO> {
        fun getPostId() : String
    }

    interface Presenter : PaginationBaseInterface.Presenter {

    }

}