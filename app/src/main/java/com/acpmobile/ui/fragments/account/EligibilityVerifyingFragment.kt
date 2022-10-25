package com.acpmobile.ui.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acpmobile.databinding.FragmentEligibilityVerifyingBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EligibilityVerifyingFragment : Fragment() {

    private var _binding: FragmentEligibilityVerifyingBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            FragmentEligibilityVerifyingBinding.inflate(inflater, container, false)
        val view = binding.root
        val mActivity =  activity as MainActivity
        navigation.activity = mActivity
        mActivity.hideToolbar()

        binding.btnCancel.setOnClickListener {
            navigation.back()
        }

        return view
    }
}