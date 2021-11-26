package com.example.glicemapapp.ui.main.settings

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
import com.example.glicemapapp.R
import com.example.glicemapapp.data.Result
import com.example.glicemapapp.data.models.RegisterDoctorRequest
import com.example.glicemapapp.data.network.handleException
import com.example.glicemapapp.ui.base.ToolbarFragment
import com.example.glicemapapp.databinding.FragmentSettingsDeleteDoctorBinding
import com.example.glicemapapp.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar

class SettingsDeleteDoctorFragment : ToolbarFragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    private var _binding: FragmentSettingsDeleteDoctorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsDeleteDoctorBinding.inflate(inflater, container, false)
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
            deleteButton.setOnClickListener {
                createDialog()
            }
        }
    }

    private fun createDialog(){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_message_two_red)
        dialog.findViewById<Button>(R.id.yes).setOnClickListener {
            deleteDoctor()
        }


        dialog.findViewById<Button>(R.id.no).setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.title).text = "Tem Certeza?"
        dialog.findViewById<TextView>(R.id.description).text = "Ao excluir seu médico você terá que cadastrá-lo de novo se desejar retomar seu acompanhamento."
        dialog.findViewById<ImageView>(R.id.icon).setImageDrawable(context?.getDrawable(R.drawable.ic_error_outline_black_24dp))
        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()



    }

    private fun deleteDoctor(){
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.bringToFront()
        settingsViewModel.deleteDoctor().observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.INVISIBLE
            val result = it?.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let {
                            if (it){
                                val i = Intent(
                                    this.context,
                                    MainActivity::class.java
                                )
                                i.putExtra("document", settingsViewModel.user.documentNumber)
                                startActivity(i)
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
}