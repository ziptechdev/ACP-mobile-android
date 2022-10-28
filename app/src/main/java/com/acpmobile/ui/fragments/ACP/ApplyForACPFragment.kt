package com.acpmobile.ui.fragments.ACP

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.VerifiedInputEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.acpmobile.databinding.FragmentApplyForACPBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApplyForACPFragment : Fragment() {

    private var _binding: FragmentApplyForACPBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentApplyForACPBinding.inflate(inflater, container, false)
        val view = binding.root
        val choosePlanCard = binding.cvChoosePlan
        val chooseDeviceCard = binding.cvChooseDevice
        val choosePlanTextToExpend = binding.tvShowText
        val choosePlanAcceptIcon = binding.ivAcceptChoosePlan
        val chooseDeviceAcceptIcon = binding.ivAcceptChooseDevice

        choosePlanCard.setOnClickListener {
            if (choosePlanTextToExpend.isVisible) {
                choosePlanTextToExpend.visibility = View.GONE
                choosePlanAcceptIcon.visibility = View.GONE
            } else {
                choosePlanTextToExpend.visibility = View.VISIBLE
                choosePlanAcceptIcon.visibility = View.GONE
            }
        }

        chooseDeviceCard.setOnClickListener {
            if (chooseDeviceAcceptIcon.isVisible)
                chooseDeviceAcceptIcon.visibility = View.GONE
            else
                chooseDeviceAcceptIcon.visibility = View.VISIBLE
        }
        return view
    }

}