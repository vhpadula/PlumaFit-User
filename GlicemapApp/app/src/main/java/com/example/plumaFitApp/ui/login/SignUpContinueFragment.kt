package com.example.plumaFitApp.ui.login

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.plumaFitApp.R
import com.example.plumaFitApp.data.Result
import com.example.plumaFitApp.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.plumaFitApp.data.network.handleException
import com.example.plumaFitApp.databinding.FragmentSignupContinueBinding
import com.example.plumaFitApp.ui.base.DateInputMask
import com.example.plumaFitApp.ui.base.ToolbarFragment


class SignUpContinueFragment : ToolbarFragment() {
    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentSignupContinueBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel =
            ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        _binding = FragmentSignupContinueBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setListeners()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setListeners() {

       val mask = DateInputMask(binding.birthdateEt).listen()


        binding.run {
            confirmButton.setOnClickListener {
                if (documentEt.text.isNullOrEmpty() || birthdateEt.text.isNullOrEmpty() || weightEt.text.toString().isNullOrEmpty() || heightEt.text.isNullOrEmpty() || sugarminEt.text.isNullOrEmpty() || sugarmaxEt.text.isNullOrEmpty()){
                    Snackbar.make(
                        binding.root,
                        "Os campos não podem ser vazios",
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    loginViewModel.documentNumber = documentEt.text.toString()
                    val sdf = SimpleDateFormat("dd/MM/yyyy")
                    val sdfApi = SimpleDateFormat("yyyy-MM-dd")
                    val date = sdf.parse(birthdateEt.text.toString())
                    loginViewModel.birthdate = sdfApi.format(date)
                    loginViewModel.weight = weightEt.text.toString().toInt()
                    loginViewModel.height = heightEt.text.toString().toInt()
                    loginViewModel.sugarMin = sugarminEt.text.toString().toInt()
                    loginViewModel.sugarmax = sugarmaxEt.text.toString().toInt()
                    registerUser()
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


    private fun createDialog(){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_message_one)
        dialog.findViewById<Button>(R.id.yes).setOnClickListener {
            val preferences =
                requireContext().getSharedPreferences("login", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("remember", "true")
            editor.putString("document", loginViewModel.documentNumber)
            editor.apply()
            val i = Intent(
                this.context,
                MainActivity::class.java
            )
            startActivity(i)
        }
        dialog.findViewById<TextView>(R.id.title).text = "Cadastro Confirmado!"
        dialog.findViewById<TextView>(R.id.description).text = "Você já pode começar a anotar as suas medições de glicemia de forma mais rápida e prática!"
        dialog.findViewById<ImageView>(R.id.icon).setImageDrawable(context?.getDrawable(R.drawable.ic_check_circle_black_24dp))
        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()



    }

}