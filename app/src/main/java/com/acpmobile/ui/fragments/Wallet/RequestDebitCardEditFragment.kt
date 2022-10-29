package com.acpmobile.ui.fragments.Wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.acpmobile.databinding.FragmentRequestDebitCardEditBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestDebitCardEditFragment : Fragment() {
    private var _binding: FragmentRequestDebitCardEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentRequestDebitCardEditBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnSave.setOnClickListener{
            val streetNumberAndName = binding.etStreetNumberAndName.editText?.text.toString()
            val city = binding.etCity.editText?.text.toString()
            val state = binding.etState.editText?.text.toString()
            val zip = binding.etZipCode.editText?.text.toString()
            val phoneNumber = binding.etPhoneNumber.editText?.text.toString()

            if(streetNumberAndName.isNotEmpty() && city.isNotEmpty() && state.isNotEmpty()
                && zip.isNotEmpty() && phoneNumber.isNotEmpty())
                    TODO("vratiti se na my wallet screen")
        }

        return view
    }
}