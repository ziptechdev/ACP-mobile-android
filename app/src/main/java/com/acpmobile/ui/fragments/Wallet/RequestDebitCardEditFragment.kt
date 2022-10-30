package com.acpmobile.ui.fragments.Wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.acpmobile.R
import com.acpmobile.databinding.FragmentRequestDebitCardEditBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RequestDebitCardEditFragment : Fragment() {
    private var _binding: FragmentRequestDebitCardEditBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentRequestDebitCardEditBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity

        val mActivity = activity as MainActivity

        mActivity.setToolbarTitle(getString(R.string.label_my_wallet))

        context?.let {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(it, R.color.white)
        }

        binding.btnSave.setOnClickListener{
            val streetNumberAndName = binding.etStreetNumberAndName.editText?.text.toString()
            val city = binding.etCity.editText?.text.toString()
            val state = binding.etState.editText?.text.toString()
            val zip = binding.etZipCode.editText?.text.toString()
            val phoneNumber = binding.etPhoneNumber.editText?.text.toString()

            if(streetNumberAndName.isNotEmpty() && city.isNotEmpty() && state.isNotEmpty()
                && zip.isNotEmpty() && phoneNumber.isNotEmpty()){
                navigation.back()
            }
        }


        return view
    }
}