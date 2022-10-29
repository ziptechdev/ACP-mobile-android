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
import com.acpmobile.ui.fragments.account.ConfirmEmailBottomSheetDialog
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentLoginBinding.inflate(inflater, container, false)
        navigation.activity = activity as MainActivity

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

        binding.btnLogin.setOnClickListener {
            navigation.openProfileFromLogin()
        }

        return binding.root
    }

}