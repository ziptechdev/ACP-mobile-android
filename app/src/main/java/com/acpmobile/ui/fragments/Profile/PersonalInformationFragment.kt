package com.acpmobile.ui.fragments.Profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.acpmobile.R
import com.acpmobile.databinding.FragmentPersonalInformationBinding
import com.acpmobile.ui.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PersonalInformationFragment : Fragment(), TextWatcher{
    private var _binding: FragmentPersonalInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalInformationBinding.inflate(inflater, container, false)
        val view = binding.root
        val mActivity = activity as MainActivity

        mActivity.setToolbarTitle(getString(R.string.label_profile))

        binding.tiEtFirstName.addTextChangedListener(this)
        binding.tiEtLastName.addTextChangedListener(this)
        binding.tiEtMonth.addTextChangedListener(this)
        binding.tiEtDayOfBirth.addTextChangedListener(this)
        binding.tiEtYearOfBirth.addTextChangedListener(this)
        binding.tiEtStreetNumberAndName.addTextChangedListener(this)
        binding.tiEtCity.addTextChangedListener(this)
        binding.tiEtState.addTextChangedListener(this)
        binding.tiEtZipCode.addTextChangedListener(this)
        binding.tiEtSSN.addTextChangedListener(this)

        binding.btnSave.setOnClickListener {
            val name = binding.etFirstName.editText?.text.toString()
            val lastName = binding.etLastName.editText?.text.toString()
            val month = binding.etMonthOfBirth.editText?.text.toString()
            val day = binding.etDayOfBirth.editText?.text.toString()
            val year = binding.etYearOfBirth.editText?.text.toString()
            val streetNumberAndName = binding.etStreetNumberAndName.editText?.text.toString()
            val city = binding.etCity.editText?.text.toString()
            val state = binding.etState.editText?.text.toString()
            val zip = binding.etZipCode.editText?.text.toString()
            val ssn = binding.etSSN.editText?.text.toString()

            if (name.isEmpty()) {
                binding.etFirstName.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etFirstName.requestFocus()
            } else {
                binding.etFirstName.error = null
            }

            if (lastName.isEmpty()) {
                binding.etLastName.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etLastName.requestFocus()
            } else {
                binding.etLastName.error = null
            }

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

            if (streetNumberAndName.isEmpty()) {
                binding.etStreetNumberAndName.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etStreetNumberAndName.requestFocus()
            } else {
                binding.etStreetNumberAndName.error = null
            }

            if (city.isEmpty()) {
                binding.etCity.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etCity.requestFocus()
            } else {
                binding.etCity.error = null
            }

            if (state.isEmpty()) {
                binding.etState.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etState.requestFocus()
            } else {
                binding.etState.error = null
            }

            if (zip.isEmpty()) {
                binding.etZipCode.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etZipCode.requestFocus()
            } else {
                binding.etZipCode.error = null
            }

            if (ssn.isEmpty()) {
                binding.etSSN.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etSSN.requestFocus()
            } else {
                binding.etSSN.error = null
            }

            if (name.isNotEmpty() && lastName.isNotEmpty() && month.isNotEmpty() && day.isNotEmpty()
                && year.isNotEmpty() && streetNumberAndName.isNotEmpty() && city.isNotEmpty()
                && state.isNotEmpty() && zip.isNotEmpty() && ssn.isNotEmpty()
            )
                TODO("Vratiti se na profile screen")
        }

        val stateSpinner = binding.tiEtState
        val adapter = ArrayAdapter(
            (activity as MainActivity),
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.US_states)
        )
        stateSpinner.setAdapter(adapter)

        val monthSpinner = binding.tiEtMonth
        val adapter2 = ArrayAdapter(
            (activity as MainActivity),
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.Months)
        )
        monthSpinner.setAdapter(adapter2)
        return view
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {
        when (p0) {
            binding.etFirstName.editText?.editableText -> if (!binding.etFirstName.editText?.text.isNullOrEmpty()) {
                binding.etFirstName.error = null
            } else {
                binding.etFirstName.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etFirstName.requestFocus()
            }

            binding.etLastName.editText?.editableText -> if (!binding.etLastName.editText?.text.isNullOrEmpty()) {
                binding.etLastName.error = null
            } else {
                binding.etLastName.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etLastName.requestFocus()
            }

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

            binding.etStreetNumberAndName.editText?.editableText -> if (!binding.etStreetNumberAndName.editText?.text.isNullOrEmpty()) {
                binding.etStreetNumberAndName.error = null
            } else {
                binding.etStreetNumberAndName.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etStreetNumberAndName.requestFocus()
            }

            binding.etCity.editText?.editableText -> if (!binding.etCity.editText?.text.isNullOrEmpty()) {
                binding.etCity.error = null
            } else {
                binding.etCity.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etCity.requestFocus()
            }

            binding.etState.editText?.editableText -> if (!binding.etState.editText?.text.isNullOrEmpty()) {
                binding.etState.error = null
            } else {
                binding.etState.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etState.requestFocus()
            }

            binding.etZipCode.editText?.editableText -> if (!binding.etZipCode.editText?.text.isNullOrEmpty()) {
                binding.etZipCode.error = null
            } else {
                binding.etZipCode.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etZipCode.requestFocus()
            }

            binding.etSSN.editText?.editableText -> if (!binding.etSSN.editText?.text.isNullOrEmpty()) {
                binding.etSSN.error = null
            } else {
                binding.etSSN.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etSSN.requestFocus()
            }
        }
    }
}