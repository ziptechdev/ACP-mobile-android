package com.acpmobile.ui.fragments.welcomeFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentWelcomeSecondBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import com.acpmobile.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeFragmentSecond : Fragment() {

    private var _binding: FragmentWelcomeSecondBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    @Inject
    lateinit var helper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeSecondBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity
        navigation.activity?.showToolbar(true)
        context?.let {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(it, R.color.white)
        }

        //TODO Remove this
        Log.i("aaabyy", "  " + helper.getString("aa", " "))

        binding.btnWelcomeSecondAccept.setOnClickListener {
            navigation.openCheckEligibilityOrCreateAccount()
        }

        return view
    }

}