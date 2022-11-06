package com.acpmobile.ui.fragments.account

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentBankInfoBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.text.TextWatcher

@AndroidEntryPoint
class BankInfoFragment : Fragment(), TextWatcher {

    private var _binding: FragmentBankInfoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBankInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        val mActivity = activity as MainActivity
        mActivity.showToolbar(true)
        mActivity.hideActionsToolbar(
            isBackVisible = true,
            isLeftTitleVisible = true,
            isTitleVisible = true,
            isCloseVisible = true
        )

        context?.let {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(it, R.color.white)
        }

        navigation.activity = mActivity

        binding.containerViewPersonalIdentityBankInfo.tvBankInfo.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )
        binding.containerViewPersonalIdentityBankInfo.tvBankInfo.setBackgroundResource(R.drawable.round_element_6)

        binding.tiEtBankName.addTextChangedListener(this)
        binding.tiEtBankNumber.addTextChangedListener(this)
        binding.tiEtAccountHolderName.addTextChangedListener(this)
        binding.tiEtAccountNumber.addTextChangedListener(this)
        binding.tiEtExpirationDate.addTextChangedListener(this)

        binding.btnComplete.setOnClickListener {
            val bankName = binding.etBankName.editText?.text.toString()
            val bankNumber = binding.etBankNumber.editText?.text.toString()
            val accountHolderName = binding.etAccountHolderName.editText?.text.toString()
            val accountNumber = binding.etAccountNumber.editText?.text.toString()
            val expirationDate = binding.etExpirationDate.editText?.text.toString()

            if (bankName.isEmpty()) {
                binding.etBankName.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etBankName.requestFocus()
            } else {
                binding.etBankName.error = null
            }

            if (bankNumber.isEmpty()) {
                binding.etBankNumber.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etBankNumber.requestFocus()
            } else {
                binding.etBankNumber.error = null
            }

            if (accountHolderName.isEmpty()) {
                binding.etAccountHolderName.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etAccountHolderName.requestFocus()
            } else {
                binding.etAccountHolderName.error = null
            }

            if (accountNumber.isEmpty()) {
                binding.etAccountNumber.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etAccountNumber.requestFocus()
            } else {
                binding.etAccountNumber.error = null
            }

            if (expirationDate.isEmpty()) {
                binding.etExpirationDate.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etExpirationDate.requestFocus()
            } else {
                binding.etExpirationDate.error = null
            }

            if (bankName.isNotEmpty() && bankNumber.isNotEmpty() && accountHolderName.isNotEmpty()
                && accountNumber.isNotEmpty() && expirationDate.isNotEmpty()
            )
                navigation.openRegistrationComplete()
        }

        return view
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {
        when (p0) {
            binding.etBankName.editText?.editableText -> if (!binding.etBankName.editText?.text.isNullOrEmpty()) {
                binding.etBankName.error = null
            } else {
                binding.etBankName.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etBankName.requestFocus()
            }

            binding.etBankNumber.editText?.editableText -> if (!binding.etBankNumber.editText?.text.isNullOrEmpty()) {
                binding.etBankNumber.error = null
            } else {
                binding.etBankNumber.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etBankNumber.requestFocus()
            }

            binding.etAccountHolderName.editText?.editableText -> if (!binding.etAccountHolderName.editText?.text.isNullOrEmpty()) {
                binding.etAccountHolderName.error = null
            } else {
                binding.etAccountHolderName.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etAccountHolderName.requestFocus()
            }

            binding.etAccountNumber.editText?.editableText -> if (!binding.etAccountNumber.editText?.text.isNullOrEmpty()) {
                binding.etAccountNumber.error = null
            } else {
                binding.etAccountNumber.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etAccountNumber.requestFocus()
            }

            binding.etExpirationDate.editText?.editableText -> if (!binding.etExpirationDate.editText?.text.isNullOrEmpty()) {
                binding.etExpirationDate.error = null
            } else {
                binding.etExpirationDate.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etExpirationDate.requestFocus()
            }
        }
    }

}