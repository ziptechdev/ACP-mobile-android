package com.acpmobile.ui.fragments.splashFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acpmobile.databinding.FragmentSplashBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null

    @Inject
    lateinit var navigation: Navigation

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
        navigation.activity = activity as MainActivity

        binding.btnGetStarted.setOnClickListener{
            navigation.openWelcomeFirsFragment()
        }
        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}