package com.example.plumaFitApp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.plumaFitApp.databinding.ActivitySplashBinding
import com.example.plumaFitApp.ui.login.LoginActivity
import com.example.plumaFitApp.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handle = Handler()
        handle.postDelayed(Runnable { val  preferences = getSharedPreferences("login", MODE_PRIVATE)
            if(preferences.getString("remember", "") == "true"){
                val i = Intent(
                    this,
                    MainActivity::class.java
                )
                startActivity(i)
            } else {
                val i = Intent(
                    this,
                    LoginActivity::class.java
                )
                startActivity(i)
            } }, 1000)


        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)




    }



}