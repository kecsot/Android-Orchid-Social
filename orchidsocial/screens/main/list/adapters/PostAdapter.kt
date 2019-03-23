package com.kecsot.orchidsocial.screens.main.list.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kecsot.orchidsocial.R
import com.kecsot.orchidsocial.models.PostDTO
import com.kecsot.orchidsocial.screens.post.PostActivity
import com.kecsot.orchidsocial.utils.DateUtil
import com.kecsot.orchidsocial.utils.GlideUtil
import kotlinx.android.synthetic.main.listpost_item.view.*


class PostAdapter(private val myDataset: List<PostDTO>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.listpost_item, parent, false) as View

        return ViewHolder(row)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = myDataset[position]
        val context = holder.view.context

        holder.view.postItemOwnerName.text = item.owner?.name
        holder.view.postItemCommentsCount.text = item.commentCount?.toString()

        item.created?.let {
            holder.view.postItemDate.text = DateUtil.getFormattedTime(it)
        }


        GlideUtil.loadProfileImage(
                context,
                item.owner?.image?.small,
                holder.view.postItemOwnerImage
        )


        item.images?.let {
            val isImageFound = it.size > 0

            if (isImageFound) {
                loadImage(context,
                        it[0].normal,
                        holder.view.postItemImageView)
            }
        }


        // Open Post
        holder.view.setOnClickListener {
            val intent = Intent(context, PostActivity::class.java)
            intent.putExtra(PostActivity.INTENT_POST_ID, item.postId)

            context.startActivity(intent)
        }

    }

    override fun getItemCount() = myDataset.size

    fun loadImage(context: Context, url: String?, image: ImageView) {
        val requestOptions = RequestOptions()
                .error(R.drawable.ic_user_placeholder_64dp)
                .diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(image)
    }
}