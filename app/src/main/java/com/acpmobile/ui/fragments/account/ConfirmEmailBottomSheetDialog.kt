package com.acpmobile.ui.fragments.account

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    val VERIFICATION_CODE = "verificationCode"
    var verificationCode: Int? = null

    @Inject
    lateinit var navigation: Navigation

    override fun getTheme() = R.style.NoBackgroundDialogTheme

    fun newInstance(verificationCode: Int): ConfirmEmailBottomSheetDialog {
        val args = Bundle()
        args.putInt(VERIFICATION_CODE, verificationCode)
        val fragment = ConfirmEmailBottomSheetDialog()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            ConfirmEmailBottomSheetDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity

        arguments?.getInt(VERIFICATION_CODE).let {
            verificationCode = it
        }

        binding.clContainer.setBackgroundResource(R.drawable.confirm_email_shape)

        binding.btnConfirm.setOnClickListener {
            val code1 = binding.etCodeFirst.editText?.text.toString()
            val code2 = binding.etCodeSecond.editText?.text.toString()
            val code3 = binding.etCodeThird.editText?.text.toString()
            val code4 = binding.etCodeFourth.editText?.text.toString()
            val code5 = binding.etCodeFifth.editText?.text.toString()

            if (code1.isNotEmpty() && code2.isNotEmpty() && code3.isNotEmpty()
                && code4.isNotEmpty() && code5.isNotEmpty()
            ) {
                val code = (code1 + code2 + code3 + code4 + code5).toInt()
                if (verificationCode == code) {
                    navigation.openIdentityProof()
                } else {
                    Toast.makeText(context, "Code doesn't match", Toast.LENGTH_SHORT).show()
                }

            }
        }

        binding.ivClose.setOnClickListener {
            dismiss()
        }

        binding.tiEtCodeFirst.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.edittext_bottom_shape_default)
        binding.tiEtCodeSecond.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.edittext_bottom_shape_default)
        binding.tiEtCodeThird.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.edittext_bottom_shape_default)
        binding.tiEtCodeFourth.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.edittext_bottom_shape_default)
        binding.tiEtCodeFifth.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.edittext_bottom_shape_default)

        binding.tiEtCodeFirst.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.tiEtCodeFirst.text.isNullOrEmpty()) {
                    binding.tiEtCodeFirst.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.edittext_shape_invalid
                        )
                } else {
                    binding.tiEtCodeFirst.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.edittext_shape_valid)

                }
            }
        })

        binding.tiEtCodeSecond.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.tiEtCodeFirst.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.edittext_bottom_shape_default
                    )
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.tiEtCodeSecond.text.isNullOrEmpty()) {
                    binding.tiEtCodeSecond.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.edittext_shape_invalid
                        )
                } else {
                    binding.tiEtCodeSecond.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.edittext_shape_valid)

                }
            }
        })


        binding.tiEtCodeThird.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.tiEtCodeSecond.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.edittext_bottom_shape_default
                    )
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.tiEtCodeThird.text.isNullOrEmpty()) {
                    binding.tiEtCodeThird.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.edittext_shape_invalid
                        )
                } else {
                    binding.tiEtCodeThird.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.edittext_shape_valid)

                }
            }
        })

        binding.tiEtCodeFourth.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.tiEtCodeThird.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.edittext_bottom_shape_default
                    )
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.tiEtCodeFourth.text.isNullOrEmpty()) {
                    binding.tiEtCodeFourth.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.edittext_shape_invalid
                        )
                } else {
                    binding.tiEtCodeFourth.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.edittext_shape_valid)

                }
            }
        })

        binding.tiEtCodeFifth.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.tiEtCodeFourth.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.edittext_bottom_shape_default
                    )
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.tiEtCodeFifth.text.isNullOrEmpty()) {
                    binding.tiEtCodeFifth.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.edittext_shape_invalid
                        )
                } else {
                    binding.tiEtCodeFifth.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.edittext_shape_valid)

                }
            }
        })

        binding.tv1.setOnClickListener() {
            addNumberToEditText("1")
        }
        binding.tv2.setOnClickListener() {
            addNumberToEditText("2")
        }

        binding.tv3.setOnClickListener() {
            addNumberToEditText("3")
        }

        binding.tv4.setOnClickListener() {
            addNumberToEditText("4")
        }

        binding.tv5.setOnClickListener() {
            addNumberToEditText("5")
        }

        binding.tv6.setOnClickListener() {
            addNumberToEditText("6")
        }

        binding.tv7.setOnClickListener() {
            addNumberToEditText("7")
        }

        binding.tv8.setOnClickListener() {
            addNumberToEditText("8")
        }

        binding.tv9.setOnClickListener() {
            addNumberToEditText("9")
        }

        binding.tv0.setOnClickListener() {
            addNumberToEditText("0")
        }

        binding.ivRemove.setOnClickListener() {
            removeNumber()
        }

        return view
    }


    fun addNumberToEditText(number: String) {
        if (binding.tiEtCodeFirst.text.isNullOrEmpty()) {
            binding.tiEtCodeFirst.setText(number)
            return
        }
        if (binding.tiEtCodeSecond.text.isNullOrEmpty()) {
            binding.tiEtCodeSecond.setText(number)
            return
        }
        if (binding.tiEtCodeThird.text.isNullOrEmpty()) {
            binding.tiEtCodeThird.setText(number)
            return
        }
        if (binding.tiEtCodeFourth.text.isNullOrEmpty()) {
            binding.tiEtCodeFourth.setText(number)
            return
        }
        if (binding.tiEtCodeFifth.text.isNullOrEmpty()) {
            binding.tiEtCodeFifth.setText(number)
            return
        }
    }

    private fun removeNumber() {
        if (!binding.tiEtCodeFifth.text.isNullOrEmpty()) {
            binding.tiEtCodeFifth.setText("")
            return
        }

        if (!binding.tiEtCodeFourth.text.isNullOrEmpty()) {
            binding.tiEtCodeFourth.setText("")
            return
        }

        if (!binding.tiEtCodeThird.text.isNullOrEmpty()) {
            binding.tiEtCodeThird.setText("")
            return
        }

        if (!binding.tiEtCodeSecond.text.isNullOrEmpty()) {
            binding.tiEtCodeSecond.setText("")
            return
        }

        if (!binding.tiEtCodeFirst.text.isNullOrEmpty()) {
            binding.tiEtCodeFirst.setText("")
            return
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}