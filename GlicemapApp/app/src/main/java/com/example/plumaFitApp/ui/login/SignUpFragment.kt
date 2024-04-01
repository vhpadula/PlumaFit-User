package com.example.plumaFitApp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import java.util.*

import com.example.plumaFitApp.databinding.FragmentSignupBinding
import com.example.plumaFitApp.ui.base.ToolbarFragment


class SignUpFragment : ToolbarFragment() {
    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel =
            ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setListeners()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setListeners(){
        binding.run {
            continueButton.setOnClickListener {
                if (emailEt.text.isNullOrEmpty() || nameEt.text.isNullOrEmpty() || passwordEt.text.isNullOrEmpty()){
                    Snackbar.make(
                        binding.root,
                        "Os campos não podem ser vazios",
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    if (emailEt.text.toString() == confirmEmailEt.text.toString() && passwordEt.text.toString() == confirmPasswordEt.text.toString()){
                        loginViewModel.name = nameEt.text.toString()
                        loginViewModel.lastName=surnameEt.text.toString()

                        loginViewModel.email = emailEt.text.toString()
                        loginViewModel.password=passwordEt.text.toString()
                        findNavController().navigate(SignUpFragmentDirections.toSignupcontinue())
                    } else {
                        if (emailEt.text.toString() != confirmEmailEt.text.toString()){

                            Snackbar.make(
                                binding.root,
                                "Os campos de email e confirmar email precisam ser iguais",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }

                        if (passwordEt.text.toString() != passwordEt.text.toString()){
                            Snackbar.make(
                                binding.root,
                                "Os campos de senha e confirmar senha precisam ser iguais",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }

                    }
                }



            }
        }
    }

}