package com.acpmobile.ui.fragments.welcomeFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acpmobile.databinding.FragmentWelcomeSecondBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeFragmentSecond : Fragment() {

    private var _binding: FragmentWelcomeSecondBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeSecondBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity
        navigation.activity?.showToolbar(true)

        binding.btnWelcomeSecondAccept.setOnClickListener{
            navigation.openCheckEligibilityOrCreateAccount()
        }

        return view
    }

}