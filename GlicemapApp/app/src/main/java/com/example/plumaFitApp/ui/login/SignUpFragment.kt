package com.example.plumaFitApp.ui.login

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.plumaFitApp.R
import com.example.plumaFitApp.data.Result
import com.example.plumaFitApp.data.network.handleException
import com.google.android.material.snackbar.Snackbar
import java.util.*

import com.example.plumaFitApp.databinding.FragmentSignupBinding
import com.example.plumaFitApp.ui.base.ToolbarFragment
import com.example.plumaFitApp.ui.main.MainActivity


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
                if (nameEt.text.isNullOrEmpty() || passwordEt.text.isNullOrEmpty() || documentNumberEt.text.isNullOrEmpty()){
                    Snackbar.make(
                        binding.root,
                        "Os campos não podem ser vazios",
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    if (passwordEt.text.toString() == confirmPasswordEt.text.toString()){
                        loginViewModel.name = nameEt.text.toString()
                        loginViewModel.password=passwordEt.text.toString()
                        loginViewModel.documentNumber = documentNumberEt.text.toString()
                        registerUser()
                    } else {

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


    private fun registerUser(){
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.bringToFront()
        loginViewModel.registerUser().observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.INVISIBLE
            val result = it?.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let {
                            if (it){
                                createDialog()
                            } else {
                                Snackbar.make(
                                    binding.root,
                                    "Houve um erro no cadastro, tente novamente mais tarde",
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

    private fun createDialog(){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_message_one)
        dialog.findViewById<Button>(R.id.yes).setOnClickListener {
            loginUser()

        }
        dialog.findViewById<TextView>(R.id.title).text = "Cadastro Confirmado!"
        dialog.findViewById<TextView>(R.id.description).text = "Você já pode começar a anotar seu estilo de vida mais saudável!"
        dialog.findViewById<ImageView>(R.id.icon).setImageDrawable(context?.getDrawable(R.drawable.ic_check_circle_black_24dp))
        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

}
}