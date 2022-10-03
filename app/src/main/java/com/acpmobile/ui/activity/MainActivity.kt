package com.acpmobile.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.acpmobile.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

        lateinit var navController: NavController

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
          navController = navHostFragment.navController
    }
}