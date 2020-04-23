package com.highestaim.recyclerviewwithfavorites.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.highestaim.recyclerviewwithfavorites.R
import com.highestaim.recyclerviewwithfavorites.adapter.MainPageAdapter
import com.highestaim.recyclerviewwithfavorites.conervter.Converter
import com.highestaim.recyclerviewwithfavorites.model.Model
import com.highestaim.recyclerviewwithfavorites.viewModel.FavoriteViewModel
import com.highestaim.recyclerviewwithfavorites.viewModel.InfoViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment() {

    private val adapter = MainPageAdapter()
    private val homeListViewModel: InfoViewModel? by viewModel()
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun getLayoutId() = R.layout.main_fragment

    override fun getTitle() = getString(R.string.home)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setList()
        setAdapter()
        addFavoriteListener()
    }


    private fun setList() {
        homeListViewModel?.getInfo()?.observe(viewLifecycleOwner, Observer {
            it?.let { models ->
                setFavorites(models)
            }
        })
    }

    private fun setAdapter() {
        homeListRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        homeListRecyclerView.layoutManager = layoutManager
        homeListRecyclerView.adapter = adapter
    }

    private fun addFavoriteListener() {
        adapter.onClick = {
            CoroutineScope(IO).launch {
                Converter.favEntityToModel(it)?.let { it1 ->
                    if (it.isFavorite) favoriteViewModel.insert(it1) else favoriteViewModel.delete(
                        it1
                    )
                }
            }
        }
    }

    //compare two lists from back amd local for set favorite
    private fun setFavorites(models: List<Model>) {
        favoriteViewModel.getFavorites()?.observe(viewLifecycleOwner, Observer {
            it.forEach { favorite ->
                models.forEach { model ->
                    if (favorite.modelId == model.id) {
                        model.isFavorite = true
                    }
                }
            }
            adapter.updateData(models)
        })
    }

}