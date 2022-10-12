package com.acpmobile.ui.fragments.Account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acpmobile.R
import com.acpmobile.databinding.FragmentIdentityProofBinding


class IdentityProofFragment : Fragment() {
    private var _binding: FragmentIdentityProofBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIdentityProofBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.containerCircleBar.tvCircle1.setBackgroundResource(R.drawable.circle_element_blue)

        return view
    }


}