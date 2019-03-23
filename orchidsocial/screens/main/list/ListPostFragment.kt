package com.kecsot.orchidsocial.screens.main.list

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.kecsot.orchidsocial.R
import com.kecsot.orchidsocial.base.pagination.AbstractPaginationBaseFragment
import com.kecsot.orchidsocial.events.ReloadCauseErrorEvent
import com.kecsot.orchidsocial.models.PostDTO
import com.kecsot.orchidsocial.screens.main.list.adapters.PostAdapter
import com.kecsot.orchidsocial.screens.post.comment.CommentFragment
import kotlinx.android.synthetic.main.listpost_fragment.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class ListPostFragment : AbstractPaginationBaseFragment<PostDTO, ListPostPresenter>(ListPostPresenter(LIST_ITEMS_PER_PAGE)), ListPostInterface.View {

    private var isFragmentInErrorState: Boolean? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: GridLayoutManager
    private val postList: MutableList<PostDTO> = ArrayList()

    companion object {
        const val RECYCLER_COLUMNS = 2
        const val LIST_ITEMS_PER_PAGE = 18
        const val LIST_LOAD_MORE_UNDER = 5
    }

    override fun getFragmentLayoutId(): Int {
        return R.layout.listpost_fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setTitle(R.string.app_name)

        viewManager = GridLayoutManager(context, RECYCLER_COLUMNS)
        viewAdapter = PostAdapter(postList)

        recyclerView = recycler.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        includedPostListErrorRow.setOnClickListener {
            EventBus.getDefault().post(ReloadCauseErrorEvent())
        }

        initPagination(recyclerView)
        refreshView()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_postlist, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_refresh -> {
                refreshView()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onItemsLoadingStarted() {
        includedPostListErrorRow.visibility = View.GONE
        shimmerRecyclerPlaceholder.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE

        // Clear list
        postList.clear()
        viewAdapter.notifyDataSetChanged()
    }

    override fun errorOnItemsLoading() {
        includedPostListErrorRow.visibility = View.VISIBLE
        shimmerRecyclerPlaceholder.visibility = View.GONE
        recyclerView.visibility = View.GONE

        isFragmentInErrorState = true
    }

    override fun onFirstPageLoaded() {
        includedPostListErrorRow.visibility = View.GONE
        shimmerRecyclerPlaceholder.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    override fun appendNewItems(items: List<PostDTO>) {
        val beforeCount = postList.size
        postList.addAll(items)
        viewAdapter.notifyItemRangeInserted(beforeCount, items.size)
    }

    fun refreshView() {
        isFragmentInErrorState = false
        presenter.loadFirstPage()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ReloadCauseErrorEvent) {
        isFragmentInErrorState?.let {
            if (it) {
                refreshView()
            }
        }
    }

    override fun getLoadMoreUnderSize(): Int {
        return CommentFragment.LIST_LOAD_MORE_UNDER
    }

    override fun getLastVisibleItemIndex(): Int? {
        return viewManager.findLastVisibleItemPosition()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onNextPageLoadingStarted() {
        Toast.makeText(context, "next page", Toast.LENGTH_LONG).show()
    }

    override fun noMorePage() {
        Toast.makeText(context, "no more page!!", Toast.LENGTH_LONG).show()
    }

}