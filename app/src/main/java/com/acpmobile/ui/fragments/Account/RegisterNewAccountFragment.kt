package com.acpmobile.ui.fragments.account

import android.os.Bundle
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
class RegisterNewAccountFragment : Fragment() {

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

        binding.btnRegister.setOnClickListener {
            val email = binding.etEMail.editText?.text.toString()
            val password = binding.etPassword.editText?.text.toString()
            val confirmPassword = binding.etConfirmPassword.editText?.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty())
                if (password == confirmPassword)
                    navigation.openRegisterNewAccountComplete()
        }

        return view
    }
}