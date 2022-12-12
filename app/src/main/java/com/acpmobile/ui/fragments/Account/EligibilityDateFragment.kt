package com.acpmobile.ui.fragments.account

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentEligibilityDateBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EligibilityDateFragment : Fragment(), TextWatcher {

    private var _binding: FragmentEligibilityDateBinding? = null
    private val binding get() = _binding!!


    @Inject
    lateinit var navigation: Navigation


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            FragmentEligibilityDateBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity
        val mActivity = activity as MainActivity
        mActivity.setToolbarTitle(getString(R.string.label_eligibility_check))

        binding.containerNameDateAddress.tvDateBirth.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )
        binding.containerNameDateAddress.tvDateBirth.setBackgroundResource(R.drawable.round_element_6)

        binding.tiEtMonth.addTextChangedListener(this)
        binding.tiEtDay.addTextChangedListener(this)
        binding.tiEtYear.addTextChangedListener(this)
        binding.tiEtSSNNumber.addTextChangedListener(this)

        binding.btnNext.setOnClickListener {
            val month = binding.etMonthOfBirth.editText?.text.toString()
            val day = binding.etDayOfBirth.editText?.text.toString()
            val year = binding.etYearOfBirth.editText?.text.toString()
            val ssn = binding.etSSNNumber.editText?.text.toString()

            if (month.isEmpty()) {
                binding.etMonthOfBirth.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etMonthOfBirth.requestFocus()
            } else {
                binding.etMonthOfBirth.error = null
            }

            if (day.isEmpty()) {
                binding.etDayOfBirth.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etDayOfBirth.requestFocus()
            } else {
                binding.etDayOfBirth.error = null
            }

            if (year.isEmpty()) {
                binding.etYearOfBirth.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etYearOfBirth.requestFocus()
            } else {
                binding.etYearOfBirth.error = null
            }

            if (ssn.isEmpty()) {
                binding.etSSNNumber.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etSSNNumber.requestFocus()
            } else {
                binding.etSSNNumber.error = null
            }


            if (month.isNotEmpty() && day.isNotEmpty() && year.isNotEmpty() && ssn.isNotEmpty())
                navigation.openCheckEligibilityAddress()
        }

        val monthSpinner = binding.tiEtMonth
        val adapter = ArrayAdapter(
            (activity as MainActivity),
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.Months)
        )
        monthSpinner.setAdapter(adapter)
        return view
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {
        when (p0) {
            binding.etMonthOfBirth.editText?.editableText -> if (!binding.etMonthOfBirth.editText?.text.isNullOrEmpty()) {
                binding.etMonthOfBirth.error = null
            } else {
                binding.etMonthOfBirth.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etMonthOfBirth.requestFocus()
            }

            binding.etDayOfBirth.editText?.editableText -> if (!binding.etDayOfBirth.editText?.text.isNullOrEmpty()) {
                binding.etDayOfBirth.error = null
            } else {
                binding.etDayOfBirth.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etDayOfBirth.requestFocus()
            }

            binding.etYearOfBirth.editText?.editableText -> if (!binding.etYearOfBirth.editText?.text.isNullOrEmpty()) {
                binding.etYearOfBirth.error = null
            } else {
                binding.etYearOfBirth.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etYearOfBirth.requestFocus()
            }

            binding.etSSNNumber.editText?.editableText -> if (!binding.etSSNNumber.editText?.text.isNullOrEmpty()) {
                binding.etSSNNumber.error = null
            } else {
                binding.etSSNNumber.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etSSNNumber.requestFocus()
            }

        }
    }
}