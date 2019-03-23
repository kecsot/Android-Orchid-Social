package com.kecsot.orchidsocial.screens.main.list

import com.kecsot.orchidsocial.base.pagination.PaginationBaseInterface
import com.kecsot.orchidsocial.models.PostDTO


interface ListPostInterface {

    interface View : PaginationBaseInterface.View<PostDTO> {

    }

    interface Presenter : PaginationBaseInterface.Presenter {

    }

}