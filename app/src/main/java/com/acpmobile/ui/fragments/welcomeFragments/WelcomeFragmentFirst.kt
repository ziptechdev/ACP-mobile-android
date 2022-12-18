package com.acpmobile.ui.fragments.welcomeFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentWelcomeFirstBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import com.acpmobile.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeFragmentFirst : Fragment() {

    private var _binding: FragmentWelcomeFirstBinding? = null
    private val binding get() = _binding!!


    @Inject
    lateinit var navigation: Navigation

    @Inject
    lateinit var helper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWelcomeFirstBinding.inflate(inflater, container, false)
        navigation.activity = activity as MainActivity
        navigation.activity?.showToolbar(false)
        context?.let {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(it, R.color.colorPrimary)
        }
        binding.btnWelcomeContinue.setOnClickListener {
            navigation.openWelcomeSecondFragment()
        }

        //TODO Remove this
        helper.setString("aa", "MyString")

        return binding.root

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}