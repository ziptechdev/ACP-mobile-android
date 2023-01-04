package com.acpmobile.ui.fragments.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.acpmobile.R
import com.acpmobile.data.request.LoginRequest
import com.acpmobile.databinding.FragmentLoginBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.ui.fragments.profile.viewmodels.LoginViewModel
    import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(), TextWatcher {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

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

        binding.tiEtEMail.addTextChangedListener(this)
        binding.tiEtPassword.addTextChangedListener(this)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEMail.editText?.text.toString()
            val password = binding.etPassword.editText?.text.toString()

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

            if (email.isNotEmpty() && password.isNotEmpty())
            {
                val loginRequest = LoginRequest(email, password)
                viewModel.userLogin(loginRequest)
            }
        }
        observeViewModel()
        return binding.root
    }
    private fun observeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            //TODO Uraditi nesto dok se ceka na izvrsenje
        }

        viewModel.loginUserError.observe(viewLifecycleOwner) { isError ->
            if (isError)
                Toast.makeText(
                    context,
                    context?.getString(R.string.error_message),
                    Toast.LENGTH_SHORT
                ).show()
        }

        viewModel.loginUser.observe(viewLifecycleOwner) { loginUser ->
            //TODO Sacuvati login token
            navigation.openProfileFromLogin()
        }
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
        }
    }
}