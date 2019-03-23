package com.kecsot.orchidsocial.screens.post.adapters

import android.content.Intent
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kecsot.orchidsocial.R
import com.kecsot.orchidsocial.models.ImageUrlDTO
import com.kecsot.orchidsocial.screens.imageviewer.FullScreenImageActivity
import com.kecsot.orchidsocial.utils.SquareImageView

class UrlPagerAdapter : PagerAdapter {

    private var imageUrlList: List<ImageUrlDTO> = ArrayList()

    constructor() {
    }

    constructor(imageUrls: MutableList<ImageUrlDTO>) {
        this.imageUrlList = imageUrls
    }

    override fun getCount(): Int {
        return imageUrlList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(view: ViewGroup, position: Int, `object`: Any) {
        view.removeView(`object` as View)
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val url = imageUrlList[position].normal

        val squareImageView = SquareImageView(view.context)
        squareImageView.scaleType = ImageView.ScaleType.CENTER_CROP

        val requestOptions = RequestOptions()
                // TODO placeholder lassú nethez
                .error(R.drawable.image_loading_err_400x400)
                .diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(view.context)
                .load(url)
                .apply(requestOptions)
                .into(squareImageView)

        // OnClick
        squareImageView.setOnClickListener({
            val intent = Intent(view.context, FullScreenImageActivity::class.java)
            intent.putExtra(FullScreenImageActivity.INTENT_URL, url)

            view.context.startActivity(intent)
        })

        view.addView(squareImageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)


        return squareImageView
    }
}