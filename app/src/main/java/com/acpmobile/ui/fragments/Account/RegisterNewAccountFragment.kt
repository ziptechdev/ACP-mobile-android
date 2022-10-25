package com.acpmobile.ui.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acpmobile.R
import com.acpmobile.databinding.FragmentEligibilityDateBinding
import com.acpmobile.databinding.FragmentRegisterNewAccountBinding
import com.acpmobile.ui.activity.MainActivity


class RegisterNewAccountFragment : Fragment() {


    private var _binding: FragmentRegisterNewAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding =
            FragmentRegisterNewAccountBinding.inflate(inflater, container, false)

        val mActivity = activity as MainActivity
        mActivity.setToolbarTitle(getString(R.string.label_register))

        return binding.root


    }

}