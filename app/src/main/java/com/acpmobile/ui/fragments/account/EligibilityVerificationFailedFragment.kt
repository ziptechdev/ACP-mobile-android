package com.acpmobile.ui.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acpmobile.R
import com.acpmobile.databinding.FragmentEligibilityVerificationFailedBinding
import com.acpmobile.databinding.FragmentEligibilityVerifyingSuccessBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EligibilityVerificationFailedFragment : Fragment() {

    private var _binding: FragmentEligibilityVerificationFailedBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEligibilityVerificationFailedBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity

        binding.btnTryAgain.setOnClickListener {
            navigation.back()
        }


        val mActivity = activity as MainActivity
        mActivity.showToolbar(true)
        mActivity.hideActionsToolbar(
            isBackVisible = false,
            isLeftTitleVisible = false,
            isTitleVisible = false,
            isCloseVisible = true
        )


        binding.btnNewAccout.setOnClickListener {
            navigation.openNewAccountFromVerificationFailed()
        }

        return view
    }
}