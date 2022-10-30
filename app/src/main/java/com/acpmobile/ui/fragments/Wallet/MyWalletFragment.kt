package com.acpmobile.ui.fragments.Wallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentLoginBinding
import com.acpmobile.databinding.FragmentMyWalletBinding
import com.acpmobile.databinding.FragmentProfileAfterRegistrationBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyWalletFragment : Fragment() {

    private var _binding: FragmentMyWalletBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyWalletBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity

        val mActivity = activity as MainActivity

        mActivity.setToolbarTitle(getString(R.string.label_my_wallet))

        context?.let {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(it, R.color.white)
        }

        binding.btnRequestCard.setOnClickListener(){
            navigation.openRequestCard()
        }

        return view
    }
}