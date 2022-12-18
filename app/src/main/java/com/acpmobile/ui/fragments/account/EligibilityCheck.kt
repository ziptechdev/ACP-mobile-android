package com.acpmobile.ui.fragments.account

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.data.model.NationalVerifierRequest
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

        navigation.activity?.nationalVerifierRequest = NationalVerifierRequest()

        binding.btnNext.setOnClickListener {
            navigation.activity?.nationalVerifierRequest!!.zipCode = binding.etZipCodeFirst.text.toString().plus(binding.etZipCodeSecond.text.toString())
            navigation.openCheckEligibilityName()
        }

        binding.etZipCodeFirst.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etZipCodeFirst.text.count() < 5 || binding.etZipCodeSecond.text.count() < 4) {
                    binding.clEditTextContainer.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.edittext_shape_invalid)
                    binding.btnNext.backgroundTintList =
                        ContextCompat.getColorStateList(requireContext(), R.color.grayBtn)
                    binding.btnNext.isEnabled = false
                } else {
                    binding.clEditTextContainer.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.edittext_shape_valid)
                    binding.btnNext.backgroundTintList =
                        ContextCompat.getColorStateList(requireContext(), R.color.colorPrimary)
                    binding.btnNext.isEnabled = true
                }
            }
        })

        binding.etZipCodeSecond.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etZipCodeSecond.text.count() < 4 || binding.etZipCodeFirst.text.count() < 5) {
                    binding.clEditTextContainer.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.edittext_shape_invalid)
                    binding.btnNext.backgroundTintList =
                        ContextCompat.getColorStateList(requireContext(), R.color.grayBtn)
                    binding.btnNext.isEnabled = false
                } else {
                    binding.clEditTextContainer.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.edittext_shape_valid)
                    binding.btnNext.backgroundTintList =
                        ContextCompat.getColorStateList(requireContext(), R.color.colorPrimary)
                    binding.btnNext.isEnabled = true
                }
            }
        })

        return view
    }

}