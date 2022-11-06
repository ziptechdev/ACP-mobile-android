package com.acpmobile.ui.fragments.Profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acpmobile.R
import com.acpmobile.databinding.FragmentSecurityBinding
import com.acpmobile.ui.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecurityFragment : Fragment(), TextWatcher{
    private var _binding: FragmentSecurityBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecurityBinding.inflate(inflater, container, false)
        val view = binding.root

        val mActivity = activity as MainActivity
        mActivity.setToolbarTitle(getString(R.string.label_profile))

        binding.tiEtEMail.addTextChangedListener(this)
        binding.tiEtCurrentPassword.addTextChangedListener(this)
        binding.tiEtNewPassword.addTextChangedListener(this)
        binding.tiEtNewPassword.addTextChangedListener(this)

        binding.btnSave.setOnClickListener{
            val email = binding.etEMail.editText?.text.toString()
            val currentPassword = binding.etCurrentPassword.editText?.text.toString()
            val newPassword = binding.etNewPassword.editText?.text.toString()
            val confirmNewPassword = binding.etConfirmNewPassword.editText?.text.toString()

            if (email.isEmpty()) {
                binding.etEMail.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etEMail.requestFocus()
            } else {
                binding.etEMail.error = null
            }

            if (currentPassword.isEmpty()) {
                binding.etCurrentPassword.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etCurrentPassword.requestFocus()
            } else {
                binding.etCurrentPassword.error = null
            }

            if (newPassword.isEmpty()) {
                binding.etNewPassword.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etNewPassword.requestFocus()
            } else {
                binding.etNewPassword.error = null
            }

            if (confirmNewPassword.isEmpty()) {
                binding.etConfirmNewPassword.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etConfirmNewPassword.requestFocus()
            } else {
                binding.etConfirmNewPassword.error = null
            }

            if (newPassword != confirmNewPassword) {
                binding.etNewPassword.error =
                    context?.resources?.getString(R.string.label_passwords_must_be_same)
                binding.etNewPassword.requestFocus()
                binding.etConfirmNewPassword.error =
                    context?.resources?.getString(R.string.label_passwords_must_be_same)
                binding.etConfirmNewPassword.requestFocus()
            } else {
                binding.etNewPassword.error = null
                binding.etConfirmNewPassword.error = null
            }

            if(email.isNotEmpty() && currentPassword.isNotEmpty() && newPassword.isNotEmpty()
                && confirmNewPassword.isNotEmpty())
                if(newPassword == confirmNewPassword)
                    TODO("Vratiti se na profile screen")
        }

        return view
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {
        when (p0) {
            binding.etEMail.editText?.editableText -> if (!binding.etEMail.editText?.text.isNullOrEmpty()) {
                binding.etEMail.error = null
            } else {
                binding.etEMail.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etEMail.requestFocus()
            }

            binding.etCurrentPassword.editText?.editableText -> if (!binding.etCurrentPassword.editText?.text.isNullOrEmpty()) {
                binding.etCurrentPassword.error = null
            } else {
                binding.etCurrentPassword.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etCurrentPassword.requestFocus()
            }

            binding.etNewPassword.editText?.editableText -> if (!binding.etNewPassword.editText?.text.isNullOrEmpty()) {
                binding.etNewPassword.error = null
            } else {
                binding.etNewPassword.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etNewPassword.requestFocus()
            }

            binding.etConfirmNewPassword.editText?.editableText -> if (!binding.etConfirmNewPassword.editText?.text.isNullOrEmpty()) {
                binding.etConfirmNewPassword.error = null
            } else {
                binding.etConfirmNewPassword.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etConfirmNewPassword.requestFocus()
            }
        }
    }
}