package com.acpmobile.ui.fragments.SplashFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.acpmobile.R
import com.acpmobile.databinding.FragmentSplashBinding
import com.acpmobile.ui.activity.MainActivity


class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnGetStarted.setOnClickListener{
            (activity as MainActivity).navController.navigate(R.id.action_splashFragment_to_welcomeFirstFragment)
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}