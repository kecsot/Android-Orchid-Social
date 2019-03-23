package com.kecsot.orchidsocial.screens.post.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kecsot.orchidsocial.MyApplication
import com.kecsot.orchidsocial.R
import com.kecsot.orchidsocial.models.CommentDTO
import com.kecsot.orchidsocial.utils.DateUtil
import com.kecsot.orchidsocial.utils.GlideUtil
import kotlinx.android.synthetic.main.comment_row.view.*

class CommentAdapter(private val myDataset: List<CommentDTO>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.comment_row, parent, false) as CardView
        return ViewHolder(row)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = myDataset[position]


        holder.cardView.commentEditText.text = item.message

        item.created?.let {
            holder.cardView.commentDate.text = DateUtil.getFormattedTime(it)
        }


        item.owner?.image?.let {
            holder.cardView.commentOwnerName.text = item.owner?.name
            GlideUtil.loadProfileImage(
                    holder.cardView.context,
                    it.small,
                    holder.cardView.commentOwnerImage
            )
        }
    }

    override fun getItemCount() = myDataset.size


}