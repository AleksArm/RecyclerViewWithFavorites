package com.highestaim.recyclerviewwithfavorites.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_toolbar.view.*

abstract class BaseFragment  : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutId(), container, false)
        view.toolbarTitle?.let {
            it.text = getTitle()
        }
        return view
    }

    abstract fun getLayoutId() : Int

    abstract fun getTitle() : String
}