package com.acpmobile.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.acpmobile.R
import com.acpmobile.databinding.ActivityMainBinding
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var navigation: Navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide status bar
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigation.activity = this

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.toolbar.ivLeft.setOnClickListener { onBackPressed() }
        binding.toolbarLight.ivLeft.setOnClickListener { onBackPressed() }

        binding.toolbar.ivRight.setOnClickListener { finish() }
        binding.toolbarLight.ivRight.setOnClickListener { finish() }


        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.fragmentProfileMain || destination.id == R.id.fragmentWallet ||
                destination.id == R.id.fragmentProfile) {
                binding.bottomNavigationViewMain.visibility = View.VISIBLE
            } else {
                binding.bottomNavigationViewMain.visibility = View.GONE
            }
        }

        binding.bottomNavigationViewMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    navigation.openHome()
                    return@setOnItemSelectedListener true
                }
                R.id.wallet -> {
                    navigation.openWallet()
                    return@setOnItemSelectedListener true
                }
                R.id.profile -> {
                    navigation.openProfile()
                    return@setOnItemSelectedListener true
                }
                else -> {false}
            }
        }
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