package com.acpmobile.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.acpmobile.R
import com.acpmobile.data.model.LoginUser
import com.acpmobile.data.request.KYCRequest
import com.acpmobile.data.request.NationalVerifierRequest
import com.acpmobile.data.request.UserVerificationRequest
import com.acpmobile.databinding.ActivityMainBinding
import com.acpmobile.utils.Constants
import com.acpmobile.utils.Navigation
import com.acpmobile.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    var nationalVerifierRequest: NationalVerifierRequest? = null
    var kycRequest: KYCRequest? = null
    var userVerificationRequest: UserVerificationRequest? = null
    var userData: LoginUser? = null

    @Inject
    lateinit var helper: SharedPreferencesHelper

    @Inject
    lateinit var navigation: Navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaultNightMode(MODE_NIGHT_NO)
        // Hide status bar
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigation.activity = this

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.toolbar.ivLeft.setOnClickListener { onBackPressed() }
        binding.toolbarLight.ivLeft.setOnClickListener { onBackPressed() }
        binding.toolbarProfile.ivLeft.setOnClickListener { onBackPressed() }

        binding.toolbar.ivRight.setOnClickListener { finish() }
        binding.toolbarLight.ivRight.setOnClickListener { finish() }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.fragmentProfileMain || destination.id == R.id.fragmentWallet ||
                destination.id == R.id.fragmentProfile || destination.id == R.id.fragmentRequestDebitCard
                || destination.id == R.id.fragmentRequestDebitCardEdit || destination.id == R.id.fragmentMyWalletCards
            ) {
                binding.bottomNavigationViewMain.visibility = View.VISIBLE

            } else {
                binding.bottomNavigationViewMain.visibility = View.GONE

            }

            if (destination.id == R.id.fragmentProfileMain || destination.id == R.id.fragmentWallet ||
                destination.id == R.id.fragmentProfile || destination.id == R.id.fragmentPersonalInformation || destination.id == R.id.fragmentSecurity
                || destination.id == R.id.fragmentRequestDebitCard || destination.id == R.id.fragmentRequestDebitCardEdit || destination.id == R.id.fragmentMyWalletCards
            ) {
                binding.toolbarProfile.root.visibility = View.VISIBLE
                hideToolbar()
            } else {
                binding.toolbarProfile.root.visibility = View.INVISIBLE
                showToolbar(true)
            }
        }

        binding.bottomNavigationViewMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    navigation.openHome()
                    setToolbarTitle(getString(R.string.label_home))
                    return@setOnItemSelectedListener true
                }
                R.id.wallet -> {
                    navigation.openWallet()
                    setToolbarTitle(getString(R.string.label_my_wallet))
                    return@setOnItemSelectedListener true
                }
                R.id.profile -> {
                    navigation.openProfile()
                    setToolbarTitle(getString(R.string.label_profile))
                    return@setOnItemSelectedListener true
                }
                else -> {
                    false
                }
            }
        }

        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)

        userData = helper.getUserData()

        if (helper.getString(Constants.TOKEN, "").isEmpty()) {
            graph.setStartDestination(R.id.welcomeFirstFragment)
//            graph.setStartDestination(R.id.fragmentLogin)
        } else {
            graph.setStartDestination(R.id.fragmentProfileMain)
        }

        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)
    }


    fun showToolbar(showLight: Boolean) {
        if (showLight) {
            binding.toolbarLight.root.visibility = View.VISIBLE
            binding.toolbar.root.visibility = View.INVISIBLE
        } else {
            binding.toolbarLight.root.visibility = View.INVISIBLE
            binding.toolbar.root.visibility = View.VISIBLE
        }
    }

    fun setToolbarTitle(title: String) {
        binding.toolbar.tvTitle.text = title
        binding.toolbarLight.tvTitle.text = title
        binding.toolbarProfile.tvTitle.text = title
    }

    fun hideToolbar() {
        binding.toolbarLight.root.visibility = View.INVISIBLE
        binding.toolbar.root.visibility = View.INVISIBLE
    }

    fun hideActionsToolbar(
        isBackVisible: Boolean,
        isLeftTitleVisible: Boolean,
        isTitleVisible: Boolean,
        isCloseVisible: Boolean
    ) {
        binding.toolbarLight.ivLeft.visibility = if (isBackVisible) View.VISIBLE else View.INVISIBLE
        binding.toolbarLight.tvLeftTitle.visibility =
            if (isLeftTitleVisible) View.VISIBLE else View.INVISIBLE
        binding.toolbarLight.tvTitle.visibility =
            if (isTitleVisible) View.VISIBLE else View.INVISIBLE
        binding.toolbarLight.ivRight.visibility =
            if (isCloseVisible) View.VISIBLE else View.INVISIBLE

        binding.toolbar.ivLeft.visibility = if (isBackVisible) View.VISIBLE else View.INVISIBLE
        binding.toolbar.tvLeftTitle.visibility =
            if (isLeftTitleVisible) View.VISIBLE else View.INVISIBLE
        binding.toolbar.tvTitle.visibility = if (isTitleVisible) View.VISIBLE else View.INVISIBLE
        binding.toolbar.ivRight.visibility = if (isCloseVisible) View.VISIBLE else View.INVISIBLE
    }
}