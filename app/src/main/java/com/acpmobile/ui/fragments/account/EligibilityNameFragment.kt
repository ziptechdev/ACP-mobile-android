package com.acpmobile.ui.fragments.account

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentEligibilityNameBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EligibilityNameFragment : Fragment(), TextWatcher {

    private var _binding: FragmentEligibilityNameBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            FragmentEligibilityNameBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity
        val mActivity = activity as MainActivity
        mActivity.setToolbarTitle(getString(R.string.label_eligibility_check))

        binding.containerNameDateAddress.tvName.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )

        binding.tiEtFirstName.addTextChangedListener(this)
        binding.tiEtLastName.addTextChangedListener(this)

        binding.containerNameDateAddress.tvName.setBackgroundResource(R.drawable.round_element_6)

        binding.btnNext.setOnClickListener {
            val name = binding.etFirstName.editText?.text.toString()
            val lastName = binding.etLastName.editText?.text.toString()

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


            if (name.isNotEmpty() && lastName.isNotEmpty()) {
                navigation.openCheckEligibilityDate()
            }
        }

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

        }

    }


}