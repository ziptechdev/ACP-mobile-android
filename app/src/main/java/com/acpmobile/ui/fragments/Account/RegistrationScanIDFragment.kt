package com.acpmobile.ui.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentIdentityProofBinding
import com.acpmobile.databinding.FragmentPersonalInfoBinding
import com.acpmobile.databinding.FragmentRegisterNewAccountBinding
import com.acpmobile.databinding.FragmentScanIdBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationScanIDFragment : Fragment() {

    private var _binding: FragmentScanIdBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScanIdBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity

        binding.containerViewPersonalIdentityBankInfo.tvIdentityProof.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.containerViewPersonalIdentityBankInfo.tvIdentityProof.setBackgroundResource(R.drawable.round_element_6)
        binding.containerCircleBar.ivCircle2.setBackgroundResource(R.drawable.circle_element_blue)

        binding.btnScanFront.setOnClickListener {
            navigation.openTakeSelfieFragment()
        }
        return view
    }

}