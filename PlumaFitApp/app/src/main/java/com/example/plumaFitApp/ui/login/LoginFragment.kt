package com.example.plumaFitApp.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.plumaFitApp.databinding.FragmentLoginBinding
import com.example.plumaFitApp.ui.main.MainActivity
import java.util.*

import android.content.Intent
import com.example.plumaFitApp.data.Result
import com.example.plumaFitApp.data.network.handleException
import com.google.android.material.snackbar.Snackbar


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


    private fun setListeners() {
        binding.loginButton.setOnClickListener {
            loginViewModel.documentNumber = binding.emailEt.text.toString()
            loginViewModel.password= binding.passwordEt.text.toString()
            loginUser()
        }
        binding.signupButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.toSignup())
        }
    }

    private fun loginUser(){
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.bringToFront()
        loginViewModel.loginUser().observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.INVISIBLE
            val result = it?.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let {
                            if (it != null){
                                val preferences =
                                    requireContext().getSharedPreferences("login", Context.MODE_PRIVATE)
                                val editor = preferences.edit()
                                editor.putString("remember", "true")
                                editor.putString("accessToken", it.accessToken)
                                editor.putString("sessionData", it.sessionData)
                                editor.apply()
                                val i = Intent(
                                    this.context,
                                    MainActivity::class.java
                                )
                                startActivity(i)
                            } else {
                                Snackbar.make(
                                    binding.root,
                                    "Houve um erro no login, tente novamente mais tarde",
                                    Snackbar.LENGTH_LONG
                                ).show()
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