package com.highestaim.recyclerviewwithfavorites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.highestaim.recyclerviewwithfavorites.R
import com.highestaim.recyclerviewwithfavorites.model.Model


class MainPageAdapter : RecyclerView.Adapter<MainPageAdapter.MyViewHolder>() {

    var onClick: ((Model) -> Unit)? = null
    private val items = arrayListOf<Model>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_list_adapter_view, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = items[position]
        val context = holder.itemView.context

        holder.name.text = "name = ${item.name}"
        holder.email.text = "email = ${item.email}"

        if (item.isFavorite) {
            holder.favorite.setImageDrawable(context.getDrawable(R.drawable.ic_favorite_filled))
        } else {
            holder.favorite.setImageDrawable(context.getDrawable(R.drawable.ic_favorite))
        }

        holder.favorite.setOnClickListener {
            item.isFavorite = !item.isFavorite
            onClick?.invoke(item)
            notifyItemChanged(position)
        }
    }

    fun updateData(items: List<Model>?) {
        items?.let {
            this.items.addAll(it)
            notifyDataSetChanged()
        }
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: AppCompatTextView = itemView.findViewById(R.id.name)
        val email: AppCompatTextView = itemView.findViewById(R.id.email)
        val favorite: AppCompatImageView = itemView.findViewById(R.id.favorite)
    }


}