package com.example.plumaFitApp.ui.login

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
            loginUser(binding.emailEt.text.toString(), binding.passwordEt.text.toString())
        }
        binding.signupButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.toSignup())
        }
    }

    private fun loginUser(login: String, password: String) {
//        binding.progressBar.visibility = View.VISIBLE
//        binding.progressBar.bringToFront()
//        loginViewModel.loginUser(login, password).observe(viewLifecycleOwner) {
//            binding.progressBar.visibility = View.INVISIBLE
//            val result = it?.let { result ->
//                when (result) {
//                    is Result.Success -> {
//                        result.data?.let {
//                            if (it == "0") {
//
//                                Snackbar.make(
//                                    binding.root,
//                                    "Email ou Senha Incorreto",
//                                    Snackbar.LENGTH_LONG
//                                ).show()
//                            } else {
//                                val preferences =
//                                    requireContext().getSharedPreferences("login", MODE_PRIVATE)
//                                val editor = preferences.edit()
//                                editor.putString("remember", "true")
//                                editor.putString("document", it)
//                                editor.apply()
//                                val i = Intent(
//                                    this.context,
//                                    MainActivity::class.java
//                                )
//                                startActivity(i)
//                            }
//
//                            true
//                        } ?: false
//                    }
//                    is Result.Error -> {
//                        Snackbar.make(
//                            binding.root,
//                            handleException(result.exception.message.toString()),
//                            Snackbar.LENGTH_LONG
//                        ).show()
//                        false
//                    }
//                }
//            }
//        }
        val i = Intent(
            this.context,
            MainActivity::class.java
        )
        startActivity(i)
    }

}