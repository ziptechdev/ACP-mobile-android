package com.acpmobile.ui.fragments.ACP

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentACPSuccessBinding
import com.acpmobile.databinding.FragmentApplyForServiceBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ACPSuccessFragment : Fragment() {

    private var _binding: FragmentACPSuccessBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentACPSuccessBinding.inflate(inflater, container, false)
        navigation.activity = activity as MainActivity
        val mActivity = activity as MainActivity

        mActivity.showToolbar(false)
        mActivity.hideActionsToolbar(
            isBackVisible = false,
            isLeftTitleVisible = false,
            isTitleVisible = false,
            isCloseVisible = false
        )
        context?.let {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(it, R.color.colorPrimary)
        }

        return binding.root
    }


}