package com.example.glicemapapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.glicemapapp.R
import com.example.glicemapapp.data.Repository
import com.example.glicemapapp.data.Result
import com.example.glicemapapp.data.models.DatesResponse
import com.example.glicemapapp.data.models.User
import com.example.glicemapapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val repository = Repository
    private val cpf = "22949837859"
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        getUser()
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)
    }

    private fun getUser() {
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.bringToFront()
        repository.getUser(cpf).observe(this) {
            binding.progressBar.visibility = View.INVISIBLE
            val result = it?.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let {
                            user = User(
                                it.documentNumber,
                                it.name,
                                it.email,
                                it.password,
                                it.birthdate,
                                it.height,
                                it.weight,
                                it.sugarMin,
                                it.sugarMax
                            )
                            findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_home)
                            true
                        } ?: false
                    }
                    is Result.Error -> {
                        Snackbar.make(
                            binding.root,
                            "Erro de conexão",
                            Snackbar.LENGTH_LONG
                        ).show()
                        false
                    }
                }
            }

        }
    }
}