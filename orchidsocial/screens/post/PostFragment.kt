package com.kecsot.orchidsocial.screens.post

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.crashlytics.android.Crashlytics
import com.kecsot.orchidsocial.R
import com.kecsot.orchidsocial.base.AbstractBaseFragment
import com.kecsot.orchidsocial.events.ReloadCauseErrorEvent
import com.kecsot.orchidsocial.models.ImageUrlDTO
import com.kecsot.orchidsocial.models.PostDTO
import com.kecsot.orchidsocial.screens.post.adapters.SingleImagePagerAdapter
import com.kecsot.orchidsocial.screens.post.adapters.UrlPagerAdapter
import kotlinx.android.synthetic.main.post_fragment.*
import kotlinx.android.synthetic.main.post_owner_row.*
import kotlinx.android.synthetic.main.post_owner_shimmer_row.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class PostFragment : AbstractBaseFragment<PostPresenter>(PostPresenter()), PostInterface.View {

    private lateinit var postId: String
    private var isFragmentInErrorState: Boolean? = null

    override fun getFragmentLayoutId(): Int {
        return R.layout.post_fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val intentPostId = activity?.intent?.extras?.getString(PostActivity.INTENT_POST_ID)

        intentPostId?.let {
            postId = it
            refreshView()
        } ?: run {
            activity?.finish()
        }

        includedPostErrorRow.setOnClickListener {
            EventBus.getDefault().post(ReloadCauseErrorEvent())
        }
    }


    /**
     * When we call starting to load the images
     */
    override fun onPostLoadingStarted() {
        postInfoShimmerLayout.startShimmerAnimation()
        viewPagerShimmerLayout.startShimmerAnimation()

        includedPostErrorRow.visibility = View.GONE
        includedPostInfoRow.visibility = View.GONE
        includedPostInfoShimmerRow.visibility = View.VISIBLE

        viewPagerIndicator.visibility = View.GONE
        viewPager.visibility = View.INVISIBLE
    }

    /**
     * Load images to ViewPager. Hide the indicator when the item is not enough
     */
    private fun loadImages(imageList: MutableList<ImageUrlDTO>) {

        val imageCount = imageList.size
        val isOnlyOneImageFound = imageCount == 1
        val isImageNotFound = imageCount == 0

        viewPagerShimmerLayout.stopShimmerAnimation()
        viewPagerIndicator.visibility = if (isOnlyOneImageFound || isImageNotFound) View.GONE else View.VISIBLE


        if (isImageNotFound) {
            onImageNotFound()
        } else {
            viewPager.adapter = UrlPagerAdapter(imageUrls = imageList)
            viewPagerIndicator.setViewPager(viewPager)
        }
    }

    /**
     * When the post loaded, but image not found, show an error message
     */
    fun onImageNotFound() {
        viewPagerIndicator.visibility = View.GONE
        viewPager.visibility = View.VISIBLE
        viewPager.adapter = SingleImagePagerAdapter(R.drawable.ic_user_placeholder_64dp)    // TODO Nincs kép a posthoz kép betöltése
        viewPagerIndicator.setViewPager(viewPager)
        Crashlytics.logException(Exception("Image not found to $postId post."))
    }

    /**
     * When we can't load the post, then stop the simmers
     * and show The Error Row. Load an informative image to ViewPager
     */
    override fun errorOnPostLoading() {
        postInfoShimmerLayout.stopShimmerAnimation()
        viewPagerShimmerLayout.stopShimmerAnimation()

        includedPostErrorRow.visibility = View.VISIBLE
        includedPostInfoRow.visibility = View.GONE
        includedPostInfoShimmerRow.visibility = View.GONE

        // Show error image
        viewPager.visibility = View.VISIBLE
        viewPager.adapter = SingleImagePagerAdapter(R.drawable.image_loading_err_400x400)    // TODO Informatív kép
        viewPagerIndicator.setViewPager(viewPager)

        isFragmentInErrorState = true
    }

    /**
     * When answer arrived from the presenter, hide error and loading views.
     * Then load the values to UI
     */
    override fun loadPost(postItem: PostDTO) {
        viewPagerShimmerLayout.stopShimmerAnimation()
        postInfoShimmerLayout.stopShimmerAnimation()

        postInfoOwnerName.visibility = View.VISIBLE
        postInfoDate.visibility = View.VISIBLE
        viewPager.visibility = View.VISIBLE

        includedPostInfoShimmerRow.visibility = View.GONE
        includedPostInfoRow.visibility = View.VISIBLE
        includedPostErrorRow.visibility = View.GONE

        postItem.images?.let {
            loadImages(it)
        } ?: kotlin.run {
            onImageNotFound()
        }

        postInfoOwnerName.text = postItem.owner?.name
        postInfoDate.text = postItem.created?.toString()


        val requestOptions = RequestOptions()
                .transforms(CenterCrop(), CircleCrop())
                .error(R.drawable.ic_user_placeholder_64dp)     // TODO: Profilkép
                .placeholder(R.drawable.ic_user_placeholder_64dp)   // TODO: Profilkép
                .diskCacheStrategy(DiskCacheStrategy.ALL)

        context?.let {
            Glide.with(it)
                    .load(postItem.owner?.image?.small)
                    .apply(requestOptions)
                    .into(postOwnerImage)
        }

    }

    /**
     * Reload the Fragment
     */
    fun refreshView() {
        isFragmentInErrorState = false
        onPostLoadingStarted()
        presenter.loadPostByPostId(postId)
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