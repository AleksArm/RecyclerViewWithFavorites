package com.highestaim.recyclerviewwithfavorites.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.highestaim.recyclerviewwithfavorites.R
import com.highestaim.recyclerviewwithfavorites.adapter.HomePageAdapter
import com.highestaim.recyclerviewwithfavorites.conervter.Converter
import com.highestaim.recyclerviewwithfavorites.conervter.Converter.commentsToCommonEntities
import com.highestaim.recyclerviewwithfavorites.conervter.Converter.commonEntitiesToComments
import com.highestaim.recyclerviewwithfavorites.model.CommentsModel
import com.highestaim.recyclerviewwithfavorites.viewModel.CommentsViewModel
import com.highestaim.recyclerviewwithfavorites.viewModel.FavoriteViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment() {

    private val adapter = HomePageAdapter()
    private val homeListViewModel: CommentsViewModel? by viewModel()
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
        CoroutineScope(Main).launch {
            homeListViewModel?.getMainDataFromLocal()?.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    getDataFromRemote()
                } else {
                    setFavorites(commonEntitiesToComments(it))
                }
            })
        }
    }

    private fun getDataFromRemote() {
        homeListViewModel?.getComments()?.observe(viewLifecycleOwner, Observer {
            it?.let { models ->
                setFavorites(models)
            }
        })
    }

    private fun setAdapter() {
        homeListRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        homeListRecyclerView.layoutManager = layoutManager
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
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
    private fun setFavorites(comments: List<CommentsModel>) {
        favoriteViewModel.getFavorites()?.observe(viewLifecycleOwner, Observer {
            it.forEach { favorite ->
                comments.forEach { comment ->
                    if (favorite.modelId == comment.id) {
                        comment.isFavorite = true
                    }
                }
            }
            homeListViewModel?.insertMainDataToDb(commentsToCommonEntities(comments))
            adapter.submitList(comments)
        })
    }

}