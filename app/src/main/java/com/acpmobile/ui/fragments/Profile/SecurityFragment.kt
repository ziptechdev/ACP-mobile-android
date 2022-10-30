package com.acpmobile.ui.fragments.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acpmobile.R
import com.acpmobile.databinding.FragmentSecurityBinding
import com.acpmobile.ui.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecurityFragment : Fragment() {
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

        binding.btnSave.setOnClickListener{
            val email = binding.etEMail.editText?.text.toString()
            val currentPassword = binding.etCurrentPassword.editText?.text.toString()
            val newPassword = binding.etNewPassword.editText?.text.toString()
            val confirmNewPassword = binding.etConfirmNewPassword.editText?.text.toString()

            if(email.isNotEmpty() && currentPassword.isNotEmpty() && newPassword.isNotEmpty()
                && confirmNewPassword.isNotEmpty())
                if(newPassword == confirmNewPassword)
                    TODO("Vratiti se na profile screen")


        }

        return view
    }

}