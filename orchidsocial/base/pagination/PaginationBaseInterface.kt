package com.kecsot.orchidsocial.base.pagination

interface PaginationBaseInterface {

    interface View<T> {
        fun onItemsLoadingStarted()
        fun errorOnItemsLoading()

        fun onFirstPageLoaded()

        fun onNextPageLoadingStarted()
        fun noMorePage()
        fun appendNewItems(items: List<T>)
    }

    interface Presenter {
        fun loadFirstPage()
        fun loadNextPage()
    }
}