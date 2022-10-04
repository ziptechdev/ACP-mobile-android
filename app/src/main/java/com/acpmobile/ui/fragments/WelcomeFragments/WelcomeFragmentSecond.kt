package com.acpmobile.ui.fragments.WelcomeFragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.acpmobile.R
import org.w3c.dom.Text


class WelcomeFragmentSecond : Fragment() {

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_welcome_second, container, false)

        var toolbarTitle = view.findViewById<TextView>(R.id.toolbar_title)
        toolbarTitle.setTextColor(R.color.black)
        return view;
    }


}