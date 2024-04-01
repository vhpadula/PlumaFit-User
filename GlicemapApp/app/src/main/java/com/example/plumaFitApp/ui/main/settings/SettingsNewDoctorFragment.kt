package com.example.plumaFitApp.ui.main.settings

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.plumaFitApp.R
import com.example.plumaFitApp.data.Result
import com.example.plumaFitApp.data.models.RegisterDoctorRequest
import com.example.plumaFitApp.data.network.handleException
import com.example.plumaFitApp.ui.base.ToolbarFragment
import com.example.plumaFitApp.databinding.FragmentSettingsNewDoctorBinding
import com.example.plumaFitApp.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar

class SettingsNewDoctorFragment : ToolbarFragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    private var _binding: FragmentSettingsNewDoctorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProvider(requireActivity()).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsNewDoctorBinding.inflate(inflater, container, false)
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
            saveButton.setOnClickListener {
                registerDoctor(RegisterDoctorRequest(settingsViewModel.user.documentNumber, codeEt.text.toString()))
            }
        }
    }



    private fun registerDoctor(request: RegisterDoctorRequest){
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.bringToFront()
        settingsViewModel.registerDoctor(request).observe(viewLifecycleOwner){
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
            dialog.dismiss()
            val i = Intent(
                this.context,
                MainActivity::class.java
            )
            i.putExtra("document", settingsViewModel.user.documentNumber)
            startActivity(i)
        }
        dialog.findViewById<TextView>(R.id.title).text = "Médico Cadastrado!"
        dialog.findViewById<TextView>(R.id.description).text = "Seu médico agora pode acompanhar suas medições de glicemia em tempo real!"
        dialog.findViewById<ImageView>(R.id.icon).setImageDrawable(context?.getDrawable(R.drawable.ic_check_circle_black_24dp))
        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()



    }
}