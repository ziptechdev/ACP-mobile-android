package com.acpmobile.ui.fragments.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentCheckEligibilityOrCreateAccountBinding
import com.acpmobile.databinding.FragmentLoginBinding
import com.acpmobile.ui.activity.MainActivity


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentLoginBinding.inflate(inflater, container, false)

        val mActivity = activity as MainActivity
        mActivity.setToolbarTitle(getString(R.string.label_login))
        mActivity.showToolbar(true)

        context?.let {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(it, R.color.white)
        }


        mActivity.hideActionsToolbar(
            isBackVisible = false,
            isLeftTitleVisible = false,
            isTitleVisible = true,
            isCloseVisible = true
        )

        return binding.root
    }

}