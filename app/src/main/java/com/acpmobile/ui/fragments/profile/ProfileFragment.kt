package com.acpmobile.ui.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.acpmobile.R
import com.acpmobile.databinding.FragmentProfileBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.ui.fragments.profile.viewmodels.LogoutViewModel
import com.acpmobile.utils.Constants
import com.acpmobile.utils.Navigation
import com.acpmobile.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LogoutViewModel by viewModels()

    @Inject
    lateinit var navigation: Navigation

    @Inject
    lateinit var helper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity

        val mActivity = activity as MainActivity
        mActivity.setToolbarTitle(getString(R.string.label_profile))

        binding.cvPersonalInfo.setOnClickListener {
            navigation.openPersonalInformation()
        }

        binding.cvSecurity.setOnClickListener {
            navigation.openSecurity()
        }
        binding.cvLogout.setOnClickListener {
            val token = helper.getString(Constants.TOKEN, "")
            viewModel.userLogout(token)
        }
        observeViewModel()

        return view
    }
    private fun observeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            //TODO Uraditi nesto dok se ceka na izvrsenje
        }

        viewModel.logoutUserError.observe(viewLifecycleOwner) { isError ->
            if (isError)
                Toast.makeText(
                    context,
                    context?.getString(R.string.error_message),
                    Toast.LENGTH_SHORT
                ).show()
        }

        viewModel.logoutUser.observe(viewLifecycleOwner) { logoutUser ->
           helper.setString(Constants.TOKEN, "")
            navigation.openLoginFromLogout()

        }

    }
}