package com.acpmobile.ui.fragments.user.mywallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.acpmobile.R
import com.acpmobile.databinding.FragmentMyWalletCardsBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.ui.fragments.user.mywallet.adapter.CreditCardsAdapter
import com.acpmobile.ui.fragments.user.mywallet.adapter.FundsAdapter
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyWalletCardsFragment : Fragment() {

    private var _binding: FragmentMyWalletCardsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyWalletCardsBinding.inflate(inflater, container, false)
        navigation.activity = activity as MainActivity

        context?.let {
            requireActivity().window.statusBarColor =
                ContextCompat.getColor(it, R.color.white)
        }

        binding.rvCreditCards.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CreditCardsAdapter()
        }

        binding.rvFunds.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FundsAdapter()
        }

        binding.btnNewCard.setOnClickListener(){
            navigation.openRequestCardFromMyWallet()
        }
        return binding.root
    }
}