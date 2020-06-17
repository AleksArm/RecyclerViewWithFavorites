package com.highestaim.recyclerviewwithfavorites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.highestaim.recyclerviewwithfavorites.conervter.Converter
import com.highestaim.recyclerviewwithfavorites.conervter.Converter.commentsToCommonEntities
import com.highestaim.recyclerviewwithfavorites.fragment.FavoriteFragment
import com.highestaim.recyclerviewwithfavorites.fragment.HomeFragment
import com.highestaim.recyclerviewwithfavorites.model.CommentsModel
import com.highestaim.recyclerviewwithfavorites.viewModel.CommentsViewModel
import com.highestaim.recyclerviewwithfavorites.viewModel.FavoriteViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val commentsViewModel : CommentsViewModel? by viewModel()
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBottomNavigationClickListener()
        replaceFragment(HomeFragment())
        updateLocalDb()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }

    private fun setBottomNavigationClickListener() {
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.main -> {
                    replaceFragment(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.favorite -> {
                    replaceFragment(FavoriteFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun updateLocalDb() {
        CoroutineScope(Main).launch {
            commentsViewModel?.getComments()?.observe(this@MainActivity, Observer {
                setFavorites(it)
                commentsViewModel?.insertMainDataToDb(commentsToCommonEntities(it))
            })
        }
    }

    //compare two lists from back amd local for set favorite
    private fun setFavorites(comments: List<CommentsModel>) {
        favoriteViewModel.getFavorites()?.observe(this@MainActivity, Observer {
            it.forEach { favorite ->
                comments.forEach { comment ->
                    if (favorite.modelId == comment.id) {
                        comment.isFavorite = true
                    }
                }
            }
        })
    }
}
