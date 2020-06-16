package com.highestaim.recyclerviewwithfavorites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.highestaim.recyclerviewwithfavorites.R
import com.highestaim.recyclerviewwithfavorites.model.CommentsModel
import kotlin.properties.Delegates


class HomePageAdapter : ListAdapter<CommentsModel,HomePageAdapter.MyViewHolder>(
    CommentsDiffCallBacks()
) {

    var onClick: ((CommentsModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.home_list_adapter_view, parent, false))


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = getItem(position)
        val context = holder.itemView.context

        holder.name.text = "name = ${item?.name}"
        holder.email.text = "email = ${item?.email}"

        if (item?.isFavorite == true) {
            holder.favorite.setImageDrawable(context.getDrawable(R.drawable.ic_favorite_filled))
        } else {
            holder.favorite.setImageDrawable(context.getDrawable(R.drawable.ic_favorite))
        }

        holder.favorite.setOnClickListener {
            item?.let { comment ->
                comment.isFavorite = !comment.isFavorite
                onClick?.invoke(comment)
                notifyItemChanged(position)
            }
        }
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: AppCompatTextView = itemView.findViewById(R.id.name)
        val email: AppCompatTextView = itemView.findViewById(R.id.email)
        val favorite: AppCompatImageView = itemView.findViewById(R.id.favorite)
    }


}

private class CommentsDiffCallBacks : DiffUtil.ItemCallback<CommentsModel>() {

    override fun areItemsTheSame(oldItem: CommentsModel, newItem: CommentsModel): Boolean {
        return newItem == oldItem
    }

    override fun areContentsTheSame(oldItem: CommentsModel, newItem: CommentsModel): Boolean {
        return newItem == oldItem
    }
}