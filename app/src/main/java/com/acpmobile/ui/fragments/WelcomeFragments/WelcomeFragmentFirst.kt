package com.acpmobile.ui.fragments.WelcomeFragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.acpmobile.R
import com.acpmobile.ui.activity.MainActivity


class WelcomeFragmentFirst : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_welcome_first, container, false)
        var toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        if (toolbar != null) {
            toolbar.visibility = View.VISIBLE
        }
        return view

    }


}