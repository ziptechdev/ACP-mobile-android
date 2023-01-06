package com.acpmobile.ui.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentProfileAfterRegistrationBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileAfterRegistrationFragment : Fragment() {

    private var _binding: FragmentProfileAfterRegistrationBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileAfterRegistrationBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity
        val mActivity = activity as MainActivity


        mActivity.userData?.let {
            binding.userDetails.tvUserName.text = "${it.firstName} ${it.lastName}"
        }

        mActivity.setToolbarTitle(getString(R.string.label_home))

        context?.let {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(it, R.color.white)
        }

        mActivity.showToolbar(true)

        mActivity.hideActionsToolbar(
            isBackVisible = false,
            isLeftTitleVisible = false,
            isTitleVisible = true,
            isCloseVisible = true
        )


        binding.btnApplyACP.setOnClickListener(){
            navigation.openApplyForService()
        }


        return view
    }

}