package com.acpmobile.ui.fragments.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.ConfirmEmailBottomSheetDialogBinding
import com.acpmobile.databinding.FragmentPersonalInfoBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ConfirmEmailBottomSheetDialog : BottomSheetDialogFragment() {

    private var _binding: ConfirmEmailBottomSheetDialogBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun getTheme() = R.style.NoBackgroundDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            ConfirmEmailBottomSheetDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity

        binding.clContainer.setBackgroundResource(R.drawable.confirm_email_shape)

        binding.btnConfirm.setOnClickListener {
           navigation.openIdentityProof()
        }

        binding.ivClose.setOnClickListener {
            dismiss()
        }
        return view
    }


    companion object {
        const val TAG = "ModalBottomSheet"
    }
}