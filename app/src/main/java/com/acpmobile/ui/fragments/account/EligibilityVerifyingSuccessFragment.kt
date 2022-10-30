package com.acpmobile.ui.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentEligibilityVerifyingSuccessBinding
import com.acpmobile.databinding.FragmentScanIdBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EligibilityVerifyingSuccessFragment : Fragment() {
    private var _binding: FragmentEligibilityVerifyingSuccessBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEligibilityVerifyingSuccessBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity


        val mActivity = activity as MainActivity
        mActivity.showToolbar(true)

        mActivity.hideActionsToolbar(
            isBackVisible = false,
            isLeftTitleVisible = false,
            isTitleVisible = false,
            isCloseVisible = true
        )


        binding.btnRegister.setOnClickListener {
            navigation.openRegisterNewAccount()
        }

        binding.btnCancel.setOnClickListener {
            activity?.finish()
        }
        return view
    }

}