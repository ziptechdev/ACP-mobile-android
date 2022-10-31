package com.acpmobile.ui.fragments.account

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.core.content.ContextCompat
import com.acpmobile.R
import com.acpmobile.databinding.FragmentEligibilityDateBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class EligibilityDateFragment : Fragment() {

    private var _binding: FragmentEligibilityDateBinding? = null
    private val binding get() = _binding!!
    var cal = Calendar.getInstance()


    @Inject
    lateinit var navigation: Navigation


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            FragmentEligibilityDateBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity
        val mActivity = activity as MainActivity
        mActivity.setToolbarTitle(getString(R.string.label_eligibility_check))

        binding.containerNameDateAddress.tvDateBirth.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )
        binding.containerNameDateAddress.tvDateBirth.setBackgroundResource(R.drawable.round_element_6)

        binding.btnNext.setOnClickListener {
            val month = binding.etMonthOfBirth.editText?.text.toString()
            val day = binding.etDayOfBirth.editText?.text.toString()
            val year = binding.etYearOfBirth.editText?.text.toString()
            val ssn = binding.etSSNNumber.editText?.text.toString()
            if (month.isNotEmpty() && day.isNotEmpty() && year.isNotEmpty() && ssn.isNotEmpty())
                navigation.openCheckEligibilityAddress()
        }



        val dateSetListenerMonth =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInViewMonth()
            }

        val dateSetListenerDay =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInViewDay()
            }
        val dateSetListenerYear =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInViewYear()
            }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        binding.tiEtMonth.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(requireContext(),
                    R.style.DataPickerTheme,
                    dateSetListenerMonth,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })
        binding.tiEtDay.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(requireContext(),
                    R.style.DataPickerTheme,
                    dateSetListenerDay,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })
        binding.tiEtYear.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(requireContext(),
                    R.style.DataPickerTheme,
                    dateSetListenerYear,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })

        return view
    }

    private fun updateDateInViewMonth() {
        val myFormat = "MMMM" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.tiEtMonth.setText(sdf.format(cal.getTime()))
    }

    private fun updateDateInViewDay() {
        val myFormat = "dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.tiEtDay.setText(sdf.format(cal.getTime()))
    }

    private fun updateDateInViewYear() {
        val myFormat = "yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.tiEtYear.setText(sdf.format(cal.getTime()))
    }
}