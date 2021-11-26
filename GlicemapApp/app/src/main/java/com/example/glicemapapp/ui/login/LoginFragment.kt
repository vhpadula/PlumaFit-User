package com.example.glicemapapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.example.glicemapapp.R
import com.example.glicemapapp.data.Result
import com.example.glicemapapp.data.models.DatesResponse
import com.example.glicemapapp.databinding.FragmentHomeBinding
import com.example.glicemapapp.databinding.FragmentLoginBinding
import com.example.glicemapapp.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import androidx.test.core.app.ApplicationProvider.getApplicationContext

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import com.example.glicemapapp.data.network.handleException
import com.example.glicemapapp.ui.main.home.HomeViewModel


class LoginFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel =
            ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setListeners()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setListeners(){
        binding.loginButton.setOnClickListener {
            loginUser(binding.emailEt.text.toString(),binding.passwordEt.text.toString())
        }
        binding.signupButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.toSignup())
        }
    }

    private fun loginUser(login: String, password: String){
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.bringToFront()
        loginViewModel.loginUser(login,password).observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.INVISIBLE
            val result = it?.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let {
                            if (it== "0"){

                                Snackbar.make(
                                    binding.root,
                                    "Email ou Senha Incorreto",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            } else {
                                val i = Intent(
                                    this.context,
                                    MainActivity::class.java
                                )
                                i.putExtra("document", binding.emailEt.text.toString())
                                startActivity(i)
                            }

                            true
                        } ?: false
                    }
                    is Result.Error -> {
                        Snackbar.make(
                            binding.root,
                            handleException(result.exception.message.toString()),
                            Snackbar.LENGTH_LONG
                        ).show()
                        false
                    }
                }
            }
        }
    }

}