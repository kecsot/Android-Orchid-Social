package com.kecsot.orchidsocial.base.pagination

import com.kecsot.orchidsocial.base.AbstractBasePresenter
import com.kecsot.orchidsocial.models.PaginationDTO
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Suppress("FINITE_BOUNDS_VIOLATION_IN_JAVA")
abstract class AbstractPaginationBasePresenter<T, F : AbstractPaginationBaseFragment<T, *>> : AbstractBasePresenter<F>(), PaginationBaseInterface.Presenter {

    companion object {
        var FIRST_PAGE_INDEX = 1
    }

    var actualPageIndex = FIRST_PAGE_INDEX;
    var lastPageIndex = FIRST_PAGE_INDEX;

    override fun onViewResume() {

    }

    override fun loadFirstPage() {
        actualPageIndex = FIRST_PAGE_INDEX;
        view.onItemsLoadingStarted()
        loadPage(actualPageIndex)
    }

    override fun loadNextPage() {
        if (actualPageIndex == lastPageIndex) {
            view.noMorePage()
            return
        }

        view.onNextPageLoadingStarted()
        actualPageIndex++
        loadPage(actualPageIndex)
    }

    private fun loadPage(page: Int) {

        getPaginationObservable(page)?.let {
            val call = it
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                if (page == FIRST_PAGE_INDEX) {
                                    view.onFirstPageLoaded()
                                }

                                view.appendNewItems(it.data)
                                lastPageIndex = it.lastPageIndex
                                view.isCallFinished()

                            },
                            {
                                view.errorOnItemsLoading()
                                view.isCallFinished()
                            }
                    )
            compositeDisposable.add(call)
        } ?: run() {
            Throwable("Missing abstract implementation!")
        }
    }

    abstract fun getPaginationObservable(page: Int): Observable<PaginationDTO<T>>?

}
