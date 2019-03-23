package com.kecsot.orchidsocial.screens.post.comment

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.kecsot.orchidsocial.R
import com.kecsot.orchidsocial.base.pagination.AbstractPaginationBaseFragment
import com.kecsot.orchidsocial.events.ReloadCauseErrorEvent
import com.kecsot.orchidsocial.models.CommentDTO
import com.kecsot.orchidsocial.screens.post.PostActivity
import com.kecsot.orchidsocial.screens.post.adapters.CommentAdapter
import com.kecsot.orchidsocial.utils.DialogUtil
import kotlinx.android.synthetic.main.comment_fragment.*
import kotlinx.android.synthetic.main.comment_row_input.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


open class CommentFragment : AbstractPaginationBaseFragment<CommentDTO, CommentPresenter>(CommentPresenter(LIST_ITEMS_PER_PAGE)), CommentInterface.View {

    companion object {
        const val LIST_ITEMS_PER_PAGE = 10
        const val LIST_LOAD_MORE_UNDER = 5
    }

    private lateinit var postId: String
    private var isFragmentInErrorState: Boolean? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var dialog: ProgressDialog? = null
    private val commentList: MutableList<CommentDTO> = ArrayList()


    override fun getFragmentLayoutId(): Int {
        return R.layout.comment_fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewManager = LinearLayoutManager(context)
        viewAdapter = CommentAdapter(commentList)

        recyclerView = recycler.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val intentPostId = activity?.intent?.extras?.getString(PostActivity.INTENT_POST_ID)
        intentPostId?.let {
            postId = it
        } ?: run {
            activity?.finish()
        }

        sendCommentButton.setOnClickListener {
            addCommentToPost(postId)
        }

        includedCommentErrorRow.setOnClickListener {
            EventBus.getDefault().post(ReloadCauseErrorEvent())
        }

        initPagination(recyclerView)
        presenter.loadFirstPage()
    }


    override fun onItemsLoadingStarted() {
        includedCommentErrorRow.visibility = View.GONE
        includedCommentRowInput.visibility = View.GONE
        commentsLoadingProgressBar.visibility = View.VISIBLE
    }

    override fun errorOnItemsLoading() {
        recycler.visibility = View.GONE
        includedCommentRowInput.visibility = View.GONE
        includedCommentErrorRow.visibility = View.VISIBLE
        commentsLoadingProgressBar.visibility = View.GONE

        isFragmentInErrorState = true
    }

    override fun onFirstPageLoaded() {
        recycler.visibility = View.VISIBLE
        includedCommentRowInput.visibility = View.VISIBLE
        includedCommentErrorRow.visibility = View.GONE
        commentsLoadingProgressBar.visibility = View.GONE
    }

    override fun onNextPageLoadingStarted() {
        Toast.makeText(context, "next page", Toast.LENGTH_LONG).show()
    }

    override fun noMorePage() {
        Toast.makeText(context, "no more page!!", Toast.LENGTH_LONG).show()
    }

    override fun appendNewItems(items: List<CommentDTO>) {
        commentList.addAll(items)
        viewAdapter.notifyDataSetChanged()
        isLoadingMoreWorking = false
    }

    override fun getPostId(): String {
        return postId
    }

    override fun getLoadMoreUnderSize(): Int {
        return LIST_LOAD_MORE_UNDER
    }

    override fun getLastVisibleItemIndex(): Int? {
        return (viewManager as LinearLayoutManager).findLastVisibleItemPosition()
    }


    /**
     * This method is collect the message from the UI, and
     * it try to add comment to actual post as logged in user
     */
    fun addCommentToPost(pPostId: String?) {
        /*

        Extensions.safeLet(pUserId, pPostId) { userId, postId ->

            val message = commentEditText.text.toString()
            val isLenghtValid = message.trim().length >= MIN_MESSAGE_LENGTH

            if (!isLenghtValid) {
                commentEditText.error = getString(R.string.error_minimum_character_x, MIN_MESSAGE_LENGTH)
            } else {
                //val comment = CommentDTO(postId, userId, message, Date())
                //presenter.addCommentToPost(postId, comment)
                dialog = ProgressDialog.show(context, "", getString(R.string.msg_loading), true)
            }

        } ?: kotlin.run {
            Crashlytics.logException(Exception("Error on addCommentToPost. userId ($pUserId) or postId ($pPostId) is null "))
        }
         */
    }

    /**
     * Comment Successfully added to Storage,
     * close keyboard and close the appbar.
     */
    fun successOnAddComment() {
        commentEditText.text = null
        hideSoftInputFromView()
        hideSoftKeyboard()
        recyclerView.requestFocus()
        dialog?.dismiss()
    }

    /**
     * Error happened when tried to add comment
     */
    fun errorOnAddComment() {
        dialog?.dismiss()

        context?.let {
            DialogUtil.instance.showCloseableDialog(it, getString(R.string.msg_something_happened))
        }

    }

    /**
     * Call presenter default method to refresh this fragment
     */
    private fun refreshView() {
        // isFragmentInErrorState = false
        // onCommentsLoadingStarted()
        // presenter.loadCommentsByPostId(postId)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ReloadCauseErrorEvent) {
        isFragmentInErrorState?.let {
            if (it) {
                refreshView()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}