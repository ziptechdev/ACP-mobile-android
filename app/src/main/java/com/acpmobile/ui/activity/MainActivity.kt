package com.acpmobile.ui.activity

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.acpmobile.R
import com.acpmobile.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide status bar
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.toolbar.ivLeft.setOnClickListener { onBackPressed() }

        binding.toolbarLight.ivLeft.setOnClickListener { onBackPressed() }
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
}