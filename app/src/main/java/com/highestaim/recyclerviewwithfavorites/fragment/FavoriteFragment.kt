package com.highestaim.recyclerviewwithfavorites.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.highestaim.recyclerviewwithfavorites.R
import com.highestaim.recyclerviewwithfavorites.adapter.FavoriteAdapter
import com.highestaim.recyclerviewwithfavorites.conervter.Converter
import com.highestaim.recyclerviewwithfavorites.conervter.Converter.favEntitiesToModels
import com.highestaim.recyclerviewwithfavorites.viewModel.FavoriteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteFragment : BaseFragment() {

    private val adapter = FavoriteAdapter()

    private val favoriteViewModel: FavoriteViewModel? by viewModel()

    override fun getLayoutId() = R.layout.favorite_fragment

    override fun getTitle() = "Favorite"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onViewCreated(view, savedInstanceState)
        updateList()
        setAdapter(view)
        removeFavorite()
    }

    private fun setAdapter(view: View) {
        val recyclerView = view.findViewById(R.id.favoriteList) as RecyclerView
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }


    private fun updateList() {
        favoriteViewModel?.getFavorites()?.observe(viewLifecycleOwner, Observer {
            it.let { favoriteEntity ->
                adapter.submitList(favEntitiesToModels(favoriteEntity).reversed())
            }
        })
    }

    private fun removeFavorite() {
        adapter.onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                Converter.favEntityToModel(it)?.let { it1 ->
                    favoriteViewModel?.delete(it1)
                }
                withContext(Dispatchers.Main) {
                    adapter.notifyDataSetChanged()
                }
            }

        }
    }
}