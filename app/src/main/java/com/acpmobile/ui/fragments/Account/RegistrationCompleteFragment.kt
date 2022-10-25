package com.acpmobile.ui.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentRegistrationCompleteBinding
import com.acpmobile.databinding.FragmentWelcomeSecondBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationCompleteFragment : Fragment() {

    private var _binding: FragmentRegistrationCompleteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationCompleteBinding.inflate(inflater, container, false)
        val view = binding.root

        navigation.activity = activity as MainActivity
        navigation.activity?.showToolbar(false)
        context?.let {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(it, R.color.colorPrimary)
        }


        return view
    }

}