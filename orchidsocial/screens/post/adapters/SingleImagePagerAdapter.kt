package com.kecsot.orchidsocial.screens.post.adapters

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.kecsot.orchidsocial.utils.SquareImageView

class SingleImagePagerAdapter : PagerAdapter {

    private var drawableRes: Int = 0

    constructor() {
    }

    constructor(drawableRes: Int) {
        this.drawableRes = drawableRes
    }

    override fun getCount(): Int {
        return 1
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(view: ViewGroup, position: Int, `object`: Any) {
        view.removeView(`object` as View)
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val squareImageView = SquareImageView(view.context)
        squareImageView.scaleType = ImageView.ScaleType.CENTER_CROP
        squareImageView.setImageResource(drawableRes)
        view.addView(squareImageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return squareImageView
    }
}