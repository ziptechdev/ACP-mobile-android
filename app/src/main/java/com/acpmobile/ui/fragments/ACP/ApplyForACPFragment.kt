package com.acpmobile.ui.fragments.ACP

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.VerifiedInputEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.acpmobile.R
import com.acpmobile.databinding.FragmentApplyForACPBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ApplyForACPFragment : Fragment() {

    private var _binding: FragmentApplyForACPBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentApplyForACPBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity

        val mActivity = activity as MainActivity
        mActivity.setToolbarTitle(getString(R.string.label_acp))
        mActivity.showToolbar(true)

        context?.let {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(it, R.color.white)
        }

        mActivity.hideActionsToolbar(
            isBackVisible = true,
            isLeftTitleVisible = true,
            isTitleVisible = true,
            isCloseVisible = true
        )

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
                choosePlanAcceptIcon.visibility = View.VISIBLE
            }
        }

        chooseDeviceCard.setOnClickListener {
            if (chooseDeviceAcceptIcon.isVisible)
                chooseDeviceAcceptIcon.visibility = View.GONE
            else
                chooseDeviceAcceptIcon.visibility = View.VISIBLE
        }

        binding.btnApply.setOnClickListener {
            navigation.openACPSuccess()
        }
        return view
    }

}