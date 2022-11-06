package com.acpmobile.ui.fragments.Wallet

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.acpmobile.R
import com.acpmobile.databinding.FragmentRequestDebitCardEditBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RequestDebitCardEditFragment : Fragment(), TextWatcher {
    private var _binding: FragmentRequestDebitCardEditBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentRequestDebitCardEditBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity

        val mActivity = activity as MainActivity

        mActivity.setToolbarTitle(getString(R.string.label_my_wallet))

        context?.let {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(it, R.color.white)
        }

        binding.tiEtStreetNumberAndName.addTextChangedListener(this)
        binding.tiEtCity.addTextChangedListener(this)
        binding.tiEtState.addTextChangedListener(this)
        binding.tiEtZipCode.addTextChangedListener(this)
        binding.tiEtPhoneNumber.addTextChangedListener(this)

        binding.btnSave.setOnClickListener {
            val streetNumberAndName = binding.etStreetNumberAndName.editText?.text.toString()
            val city = binding.etCity.editText?.text.toString()
            val state = binding.etState.editText?.text.toString()
            val zip = binding.etZipCode.editText?.text.toString()
            val phoneNumber = binding.etPhoneNumber.editText?.text.toString()


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

            if (phoneNumber.isEmpty()) {
                binding.etPhoneNumber.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etPhoneNumber.requestFocus()
            } else {
                binding.etPhoneNumber.error = null
            }

            if (streetNumberAndName.isNotEmpty() && city.isNotEmpty() && state.isNotEmpty()
                && zip.isNotEmpty() && phoneNumber.isNotEmpty()
            ) {
                navigation.back()
            }
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

            binding.etPhoneNumber.editText?.editableText -> if (!binding.etPhoneNumber.editText?.text.isNullOrEmpty()) {
                binding.etPhoneNumber.error = null
            } else {
                binding.etPhoneNumber.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etPhoneNumber.requestFocus()
            }
        }
    }
}