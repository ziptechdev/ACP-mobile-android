package com.acpmobile.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acpmobile.R
import com.acpmobile.utils.Constants
import com.acpmobile.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StarterActivity : AppCompatActivity() {

    @Inject
    lateinit var helper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (helper.getString(Constants.TOKEN, "").isEmpty()) {
            startActivity(Intent(this, SplashActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}