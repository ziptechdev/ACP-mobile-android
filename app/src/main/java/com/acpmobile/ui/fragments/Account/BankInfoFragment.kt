package com.acpmobile.ui.fragments.account

import android.os.Bundle
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

@AndroidEntryPoint
class BankInfoFragment : Fragment() {

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

        binding.btnComplete.setOnClickListener {
            val bankName = binding.etBankName.editText?.text.toString()
            val bankNumber = binding.etBankNumber.editText?.text.toString()
            val accountHolderName = binding.etAccountHolderName.editText?.text.toString()
            val accountNumber = binding.etAccountNumber.editText?.text.toString()
            val expirationDate = binding.etExpirationDate.editText?.text.toString()
            if (bankName.isNotEmpty() && bankNumber.isNotEmpty() && accountHolderName.isNotEmpty()
                && accountNumber.isNotEmpty() && expirationDate.isNotEmpty()
            )
                navigation.openRegistrationComplete()
        }

        return view
    }
}