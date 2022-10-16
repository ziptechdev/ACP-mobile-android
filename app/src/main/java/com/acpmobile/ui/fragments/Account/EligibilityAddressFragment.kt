package com.acpmobile.ui.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentEligibilityAddressBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EligibilityAddressFragment : Fragment() {

    private var _binding: FragmentEligibilityAddressBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            FragmentEligibilityAddressBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity

        binding.containerNameDateAddress.tvAddress.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.containerNameDateAddress.tvAddress.setBackgroundResource(R.drawable.round_element_6)

        binding.btnVerify.setOnClickListener {
            navigation.openVerifyingFragment()
        }
        return view
    }

}