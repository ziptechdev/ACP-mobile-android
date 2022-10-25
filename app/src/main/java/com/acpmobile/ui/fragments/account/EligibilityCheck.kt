package com.acpmobile.ui.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acpmobile.R
import com.acpmobile.databinding.FragmentEligibilityCheckBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EligibilityCheck : Fragment() {

    private var _binding: FragmentEligibilityCheckBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEligibilityCheckBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity
        val mActivity = activity as MainActivity
        mActivity.setToolbarTitle(getString(R.string.label_eligibility_check))

        binding.btnNext.setOnClickListener{
            navigation.openCheckEligibilityName()
        }

        return view
    }

}