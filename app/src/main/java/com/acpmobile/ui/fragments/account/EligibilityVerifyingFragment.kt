package com.acpmobile.ui.fragments.account

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.acpmobile.databinding.FragmentEligibilityVerifyingBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.ui.fragments.Account.viewmodels.NationalVerifierViewModel
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EligibilityVerifyingFragment : Fragment() {

    private var _binding: FragmentEligibilityVerifyingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NationalVerifierViewModel by viewModels()

    @Inject
    lateinit var navigation: Navigation

    var mCountDownTimer: CountDownTimer? = null

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

        viewModel.nationalVerifier(navigation.activity?.nationalVerifierRequest!!)

        //TODO Add livedata in view model and observer in this fragment

        var i = 0

        binding.progressBar.progress = i

        mCountDownTimer = object : CountDownTimer(8000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                i++
                binding.progressBar.progress = i * 100 / (8000 / 1000)
                binding.tvProgressPercent.text = binding.progressBar.progress.toString().plus("%")
            }

            override fun onFinish() {
                i++
                binding.progressBar.progress = 100
                navigation.openVerificationSuccess()

            }
        }

        mCountDownTimer?.start()

        binding.btnCancel.setOnClickListener {
            activity?.finish()
        }

        binding.ivFail.setOnClickListener {
            navigation.openVerificationFailed()
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        mCountDownTimer?.cancel()
        binding.progressBar.progress = 0
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mCountDownTimer?.cancel()
        binding.progressBar.progress = 0
    }
}