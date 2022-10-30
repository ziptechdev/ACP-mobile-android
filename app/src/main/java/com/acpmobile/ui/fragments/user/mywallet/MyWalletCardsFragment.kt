package com.acpmobile.ui.fragments.user.mywallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.acpmobile.databinding.FragmentMyWalletCardsBinding
import com.acpmobile.ui.fragments.user.mywallet.adapter.CreditCardsAdapter
import com.acpmobile.ui.fragments.user.mywallet.adapter.FundsAdapter

class MyWalletCardsFragment : Fragment() {

    private var _binding: FragmentMyWalletCardsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyWalletCardsBinding.inflate(inflater, container, false)

        binding.rvCreditCards.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CreditCardsAdapter()
        }

        binding.rvFunds.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FundsAdapter()
        }

        return binding.root
    }
}