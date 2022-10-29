package com.acpmobile.ui.fragments.Profile
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acpmobile.databinding.FragmentPersonalInformationBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PersonalInformationFragment : Fragment() {
    private var _binding: FragmentPersonalInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalInformationBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnSave.setOnClickListener{
            val name = binding.etFirstName.editText?.text.toString()
            val lastName = binding.etLastName.editText?.text.toString()
            val month = binding.etMonthOfBirth.editText?.text.toString()
            val day = binding.etDayOfBirth.editText?.text.toString()
            val year = binding.etYearOfBirth.editText?.text.toString()
            val streetNumberAndName = binding.etStreetNumberAndName.editText?.text.toString()
            val city = binding.etCity.editText?.text.toString()
            val state = binding.etState.editText?.text.toString()
            val zip = binding.etZipCode.editText?.text.toString()
            val ssn = binding.etSSN.editText?.text.toString()

            if(name.isNotEmpty() && lastName.isNotEmpty() && month.isNotEmpty() && day.isNotEmpty()
                && year.isNotEmpty() && streetNumberAndName.isNotEmpty() && city.isNotEmpty()
                && state.isNotEmpty() && zip.isNotEmpty() && ssn.isNotEmpty())
                    TODO("Vratiti se na profile screen")
        }
        return view
    }
}