package com.acpmobile.ui.fragments.welcomeFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.acpmobile.R
import com.acpmobile.databinding.FragmentWelcomeFirstBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeFragmentFirst : Fragment() {

    private var _binding: FragmentWelcomeFirstBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWelcomeFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity
        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        if (toolbar != null) {
            toolbar.visibility = View.VISIBLE
        }

        binding.btnWelcomeContinue.setOnClickListener{
            navigation.openWelcomeSecondFragment()
        }
        return view

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}