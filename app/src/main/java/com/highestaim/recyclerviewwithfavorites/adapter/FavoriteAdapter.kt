package com.highestaim.recyclerviewwithfavorites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.highestaim.recyclerviewwithfavorites.R
import com.highestaim.recyclerviewwithfavorites.model.CommentsModel
import kotlin.properties.Delegates

class FavoriteAdapter :
    RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>(),AutoUpdatableAdapter {

    var onClick: ((CommentsModel) -> Unit)? = null

    var favorites: List<CommentsModel?> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { o, n -> o?.id == n?.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_list_adapter_view, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = favorites.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = favorites[position]

        holder.name.text = "name = ${item?.name}"
        holder.email.text = "email = ${item?.email}"

        holder.favorite.setOnClickListener {
            item?.let { it1 -> onClick?.invoke(it1) }
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: AppCompatTextView = itemView.findViewById(R.id.name)
        val email: AppCompatTextView = itemView.findViewById(R.id.email)
        val favorite: AppCompatImageView = itemView.findViewById(R.id.favorite)
    }

}