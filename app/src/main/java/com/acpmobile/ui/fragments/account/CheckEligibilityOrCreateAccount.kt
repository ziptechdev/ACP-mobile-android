package com.acpmobile.ui.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acpmobile.R
import com.acpmobile.databinding.FragmentCheckEligibilityOrCreateAccountBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CheckEligibilityOrCreateAccount : Fragment() {

    private var _binding: FragmentCheckEligibilityOrCreateAccountBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            FragmentCheckEligibilityOrCreateAccountBinding.inflate(inflater, container, false)
        val view = binding.root

        val mActivity = activity as MainActivity
        mActivity.setToolbarTitle(getString(R.string.label_eligibility_check))

        mActivity.hideActionsToolbar(
            isBackVisible = true,
            isLeftTitleVisible = true,
            isTitleVisible = true,
            isCloseVisible = true
        )

        navigation.activity = activity as MainActivity

        binding.btnCheckEligibility.setOnClickListener {
            navigation.openCheckEligibility()
        }

        binding.btnNewAccount.setOnClickListener {
            navigation.openCreateNewAccountFragment()
        }

        return view
    }
}