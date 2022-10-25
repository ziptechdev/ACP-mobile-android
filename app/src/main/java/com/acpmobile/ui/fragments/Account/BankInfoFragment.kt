package com.acpmobile.ui.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentBankInfoBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BankInfoFragment : Fragment() {

    private var _binding: FragmentBankInfoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBankInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity

        binding.containerViewPersonalIdentityBankInfo.tvBankInfo.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.containerViewPersonalIdentityBankInfo.tvBankInfo.setBackgroundResource(R.drawable.round_element_6)

        binding.btnComplete.setOnClickListener {
            navigation.openRegistrationComplete()
        }

        return view
    }
}