package com.kecsot.orchidsocial.base.pagination

import android.support.v7.widget.RecyclerView
import com.kecsot.orchidsocial.base.AbstractBaseFragment
import com.kecsot.orchidsocial.base.AbstractBasePresenter


@Suppress("FINITE_BOUNDS_VIOLATION_IN_JAVA")
abstract class AbstractPaginationBaseFragment<T, P : AbstractBasePresenter<*>>(presenter: AbstractPaginationBasePresenter<*, *>) : AbstractBaseFragment<AbstractPaginationBasePresenter<*, *>>(presenter), PaginationBaseInterface.View<T> {

    var isLoadingMoreWorking = false

    fun initPagination(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = recyclerView.layoutManager.itemCount
                val lastVisibleItem = getLastVisibleItemIndex()

                lastVisibleItem?.let {
                    if (!isLoadingMoreWorking && totalItemCount <= it + getLoadMoreUnderSize()) {
                        presenter.loadNextPage()
                        isLoadingMoreWorking = true
                    }
                }
            }
        })
    }


    // Server call finished. New call enabled
    fun isCallFinished() {
        isLoadingMoreWorking = false
    }

    abstract fun getLastVisibleItemIndex(): Int?

    open fun getLoadMoreUnderSize(): Int {
        return 1
    }

}
