package com.acpmobile.ui.fragments.Profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentCheckEligibilityOrCreateAccountBinding
import com.acpmobile.databinding.FragmentLoginBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.ui.fragments.account.ConfirmEmailBottomSheetDialog
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(), TextWatcher {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentLoginBinding.inflate(inflater, container, false)
        navigation.activity = activity as MainActivity

        val mActivity = activity as MainActivity
        mActivity.setToolbarTitle(getString(R.string.label_login))
        mActivity.showToolbar(true)

        context?.let {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(it, R.color.white)
        }


        mActivity.hideActionsToolbar(
            isBackVisible = false,
            isLeftTitleVisible = false,
            isTitleVisible = true,
            isCloseVisible = true
        )

        binding.tiEtEmail.addTextChangedListener(this)
        binding.tiEtPassword.addTextChangedListener(this)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.editText?.text.toString()
            val password = binding.etPassword.editText?.text.toString()

            if (email.isEmpty()) {
                binding.etEmail.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etEmail.requestFocus()
            } else {
                binding.etEmail.error = null
            }

            if (password.isEmpty()) {
                binding.etPassword.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etPassword.requestFocus()
            } else {
                binding.etPassword.error = null
            }

            if (email.isNotEmpty() && password.isNotEmpty())
                navigation.openProfileFromLogin()
        }

        return binding.root
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {
        when (p0) {
            binding.etEmail.editText?.editableText -> if (!binding.etEmail.editText?.text.isNullOrEmpty()) {
                binding.etEmail.error = null
            } else {
                binding.etEmail.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etEmail.requestFocus()
            }

            binding.etPassword.editText?.editableText -> if (!binding.etPassword.editText?.text.isNullOrEmpty()) {
                binding.etPassword.error = null
            } else {
                binding.etPassword.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etPassword.requestFocus()
            }
        }
    }
}