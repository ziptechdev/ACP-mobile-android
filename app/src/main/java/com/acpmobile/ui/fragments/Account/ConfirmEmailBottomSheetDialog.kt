package com.acpmobile.ui.fragments.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acpmobile.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ConfirmEmailBottomSheetDialog : BottomSheetDialogFragment() {

    override fun getTheme() = R.style.NoBackgroundDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = View.inflate(requireContext(), R.layout.confirm_email_bottom_sheet_dialog, null)
        view.setBackgroundResource(R.drawable.confirm_email_shape)
        return view
    }


    companion object {
        const val TAG = "ModalBottomSheet"
    }
}