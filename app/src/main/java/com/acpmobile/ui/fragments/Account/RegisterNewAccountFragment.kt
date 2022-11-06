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
import com.acpmobile.databinding.FragmentRegisterNewAccountBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterNewAccountFragment : Fragment(), TextWatcher {

    private var _binding: FragmentRegisterNewAccountBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterNewAccountBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity

        val mActivity = activity as MainActivity
        mActivity.setToolbarTitle(getString(R.string.label_register))
        mActivity.showToolbar(true)

        context?.let {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(it, R.color.white)
        }

        binding.tiEtEMail.addTextChangedListener(this)
        binding.tiEtPassword.addTextChangedListener(this)
        binding.tiEtConfirmPassword.addTextChangedListener(this)

        binding.btnRegister.setOnClickListener {
            val email = binding.etEMail.editText?.text.toString()
            val password = binding.etPassword.editText?.text.toString()
            val confirmPassword = binding.etConfirmPassword.editText?.text.toString()

            if (email.isEmpty()) {
                binding.etEMail.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etEMail.requestFocus()
            } else {
                binding.etEMail.error = null
            }

            if (password.isEmpty()) {
                binding.etPassword.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etPassword.requestFocus()
            } else {
                binding.etPassword.error = null
            }

            if (confirmPassword.isEmpty()) {
                binding.etConfirmPassword.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etConfirmPassword.requestFocus()
            } else {
                binding.etConfirmPassword.error = null
            }

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty())
                if (password == confirmPassword)
                    navigation.openRegisterNewAccountComplete()
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

            binding.etPassword.editText?.editableText -> if (!binding.etPassword.editText?.text.isNullOrEmpty()) {
                binding.etPassword.error = null
            } else {
                binding.etPassword.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etPassword.requestFocus()
            }

            binding.etConfirmPassword.editText?.editableText -> if (!binding.etConfirmPassword.editText?.text.isNullOrEmpty()) {
                binding.etConfirmPassword.error = null
            } else {
                binding.etConfirmPassword.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etConfirmPassword.requestFocus()
            }

        }
    }
}