package com.example.glicemapapp.ui.login

import android.app.Dialog
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
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import com.example.glicemapapp.data.network.handleException
import com.example.glicemapapp.databinding.FragmentSignupBinding
import com.example.glicemapapp.databinding.FragmentSignupContinueBinding
import com.example.glicemapapp.ui.base.ToolbarFragment


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
                    loginViewModel.birthdate = birthdateEt.text.toString()
                    for (i in 0..loginViewModel.birthdate.length-1){
                        if (loginViewModel.birthdate[i] == '/'){
                            val sb = StringBuilder(loginViewModel.birthdate).also { it.setCharAt(i, '-') }
                            loginViewModel.birthdate = sb.toString()
                        }

                    }
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
            val i = Intent(
                this.context,
                MainActivity::class.java
            )
            i.putExtra("document", loginViewModel.documentNumber)
            startActivity(i)
        }
        dialog.findViewById<TextView>(R.id.title).text = "Cadastro Confirmado!"
        dialog.findViewById<TextView>(R.id.description).text = "Você já pode começar a anotar as suas medições de glicemia de forma mais rápida e prática!"
        dialog.findViewById<ImageView>(R.id.icon).setImageDrawable(context?.getDrawable(R.drawable.ic_check_circle_black_24dp))
        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()



    }

}