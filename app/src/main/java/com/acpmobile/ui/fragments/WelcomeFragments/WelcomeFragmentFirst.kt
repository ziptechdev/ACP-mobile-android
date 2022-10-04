package com.acpmobile.ui.fragments.WelcomeFragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.acpmobile.R

class WelcomeFragmentFirst : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_welcome_first, container, false)

        val welcomeSubtitle = view.findViewById<TextView>(R.id.tvWelcomeFirstSubTitle)
        welcomeSubtitle.movementMethod = ScrollingMovementMethod()
        return view
    }


}