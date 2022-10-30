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
            val code1 = binding.etCodeFirst.editText?.text.toString()
            val code2 = binding.etCodeSecond.editText?.text.toString()
            val code3 = binding.etCodeThird.editText?.text.toString()
            val code4 = binding.etCodeFourth.editText?.text.toString()
            val code5 = binding.etCodeFifth.editText?.text.toString()

            if(code1.isNotEmpty() && code2.isNotEmpty() && code3.isNotEmpty()
                && code4.isNotEmpty() && code5.isNotEmpty())
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