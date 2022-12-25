package com.acpmobile.ui.fragments.account

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.acpmobile.R
import com.acpmobile.data.request.EmailVerificationRequest
import com.acpmobile.data.request.KYCRequest
import com.acpmobile.data.model.User
import com.acpmobile.databinding.FragmentPersonalInfoBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.ui.fragments.account.viewmodels.KYCViewModel
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PersonalInfoFragment : Fragment(), TextWatcher {

    private var _binding: FragmentPersonalInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: KYCViewModel by viewModels()


    @Inject
    lateinit var navigation: Navigation


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentPersonalInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity

        val mActivity = activity as MainActivity
        mActivity.setToolbarTitle(getString(R.string.label_registration))

        mActivity.kycRequest = KYCRequest()

        mActivity.hideActionsToolbar(
            isBackVisible = true,
            isLeftTitleVisible = true,
            isTitleVisible = true,
            isCloseVisible = true
        )

        binding.containerViewPersonalIdentityBankInfo.tvPersonalInfo.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )
        binding.containerViewPersonalIdentityBankInfo.tvPersonalInfo.setBackgroundResource(R.drawable.round_element_6)

        binding.tiEtFirstName.addTextChangedListener(this)
        binding.tiEtLastName.addTextChangedListener(this)
        binding.tiEtEMail.addTextChangedListener(this)
        binding.tiEtPhoneNumber.addTextChangedListener(this)
        binding.tiEtPassword.addTextChangedListener(this)
        binding.tiEtConfirmPassword.addTextChangedListener(this)
        binding.tiEtSSN.addTextChangedListener(this)

        binding.btnNext.setOnClickListener {
            val name = binding.etFirstName.editText?.text.toString()
            val lastName = binding.etLastName.editText?.text.toString()
            val email = binding.etEMail.editText?.text.toString()
            val phoneNumber = binding.etPhoneNumber.editText?.text.toString()
            val password = binding.etPassword.editText?.text.toString()
            val confirmPassword = binding.etConfirmPassword.editText?.text.toString()
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

            if (email.isEmpty()) {
                binding.etEMail.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etEMail.requestFocus()
            } else {
                binding.etEMail.error = null
            }

            if (phoneNumber.isEmpty()) {
                binding.etPhoneNumber.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etPhoneNumber.requestFocus()
            } else {
                binding.etPhoneNumber.error = null
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

            if (ssn.isEmpty()) {
                binding.etSSN.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etSSN.requestFocus()
            } else {
                binding.etSSN.error = null
            }

            if (password != confirmPassword) {
                binding.etPassword.error =
                    context?.resources?.getString(R.string.label_passwords_must_be_same)
                binding.etPassword.requestFocus()
                binding.etConfirmPassword.error =
                    context?.resources?.getString(R.string.label_passwords_must_be_same)
                binding.etConfirmPassword.requestFocus()
            } else {
                binding.etPassword.error = null
                binding.etConfirmPassword.error = null
            }
            if (name.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && phoneNumber.isNotEmpty()
                && password.isNotEmpty() && confirmPassword.isNotEmpty() && ssn.isNotEmpty()
            ) {
                val user =
                    User(null, name, lastName, email, password, confirmPassword, phoneNumber, ssn)
                mActivity.kycRequest?.user = user
                binding.pbRequestVerificationCode.visibility = View.VISIBLE
                viewModel.verifyEmail(EmailVerificationRequest(email))

            }
        }

        observeViewModel()

        return view
    }

    private fun observeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.pbRequestVerificationCode.visibility =
                if (isLoading) View.VISIBLE else View.GONE
            binding.btnNext.isEnabled = !isLoading
        }

        viewModel.verificationError.observe(viewLifecycleOwner) { isError ->
            if (isError)
                Toast.makeText(
                    context,
                    context?.getString(R.string.error_message),
                    Toast.LENGTH_SHORT
                ).show()
        }

        viewModel.verificationCode.observe(viewLifecycleOwner) { verificationModel ->
            Log.i("VERIFICATIONRESPONSE", verificationModel.verificationCode.toString())
            val blankFragment = ConfirmEmailBottomSheetDialog().newInstance(verificationModel.verificationCode)
            blankFragment.show(childFragmentManager, blankFragment.getTag())
        }
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

            binding.etEMail.editText?.editableText -> if (!binding.etEMail.editText?.text.isNullOrEmpty()) {
                binding.etEMail.error = null
            } else {
                binding.etEMail.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etEMail.requestFocus()
            }

            binding.etPhoneNumber.editText?.editableText -> if (!binding.etPhoneNumber.editText?.text.isNullOrEmpty()) {
                binding.etPhoneNumber.error = null
            } else {
                binding.etPhoneNumber.error =
                    context?.resources?.getString(R.string.label_required_field)
                binding.etPhoneNumber.requestFocus()
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