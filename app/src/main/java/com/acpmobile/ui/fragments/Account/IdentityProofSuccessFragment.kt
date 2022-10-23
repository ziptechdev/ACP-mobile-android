package com.acpmobile.ui.fragments.Account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentIdentityProofSuccessBinding

class IdentityProofSuccessFragment : Fragment() {
    private var _binding: FragmentIdentityProofSuccessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIdentityProofSuccessBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.containerViewPersonalIdentityBankInfo.tvIdentityProof.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.containerViewPersonalIdentityBankInfo.tvIdentityProof.setBackgroundResource(R.drawable.round_element_6)
        binding.containerCircleBar.ivCircle4.setBackgroundResource(R.drawable.circle_element_blue)
        return view
        }
}