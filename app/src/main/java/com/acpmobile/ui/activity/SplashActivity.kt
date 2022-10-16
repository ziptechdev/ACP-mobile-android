package com.acpmobile.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.acpmobile.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {


    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val w = window
        w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        w.getDecorView()
            .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)

        binding.btnGetStarted.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}