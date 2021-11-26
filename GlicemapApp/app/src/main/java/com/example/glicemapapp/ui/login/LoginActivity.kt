package com.example.glicemapapp.ui.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.glicemapapp.R
import com.example.glicemapapp.data.Repository
import com.example.glicemapapp.data.Result
import com.example.glicemapapp.data.models.Doctor
import com.example.glicemapapp.data.models.User
import com.example.glicemapapp.databinding.ActivityLoginBinding
import com.example.glicemapapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)


    }



}