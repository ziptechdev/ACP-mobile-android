package com.acpmobile.ui.fragments.account

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentEligibilityAddressBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.text.TextWatcher

@AndroidEntryPoint
class EligibilityAddressFragment : Fragment(), TextWatcher {

    private var _binding: FragmentEligibilityAddressBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            FragmentEligibilityAddressBinding.inflate(inflater, container, false)
        val view = binding.root

        val mActivity = activity as MainActivity
        navigation.activity = activity as MainActivity
        mActivity.showToolbar(true)

        binding.containerNameDateAddress.tvAddress.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )
        binding.containerNameDateAddress.tvAddress.setBackgroundResource(R.drawable.round_element_6)


        binding.tiEtStreetNumber.addTextChangedListener(this)
        binding.tiEtCity.addTextChangedListener(this)
        binding.tiEtState.addTextChangedListener(this)
        binding.tiEtZipCode.addTextChangedListener(this)

        binding.btnVerify.setOnClickListener {
            val streetNumberAndName = binding.etStreetNumber.editText?.text.toString()
            val city = binding.etCity.editText?.text.toString()
            val state = binding.etState.editText?.text.toString()
            val zip = binding.etZipCode.editText?.text.toString()

            if (streetNumberAndName.isEmpty()) {
                binding.etStreetNumber.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etStreetNumber.requestFocus()
            } else {
                binding.etStreetNumber.error = null
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

            if (streetNumberAndName.isNotEmpty() && city.isNotEmpty() && state.isNotEmpty() && zip.isNotEmpty())
                navigation.openVerifyingFragment()
        }

        val stateSpinner = binding.tiEtState
        val adapter = ArrayAdapter(
            (activity as MainActivity),
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.US_states)
        )
        stateSpinner.setAdapter(adapter)
        return view
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {
        when (p0) {
            binding.etStreetNumber.editText?.editableText -> if (!binding.etStreetNumber.editText?.text.isNullOrEmpty()) {
                binding.etStreetNumber.error = null
            } else {
                binding.etStreetNumber.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etStreetNumber.requestFocus()
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

        }
    }
}