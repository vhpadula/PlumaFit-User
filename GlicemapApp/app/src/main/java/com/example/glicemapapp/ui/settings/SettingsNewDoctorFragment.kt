package com.example.glicemapapp.ui.settings

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.glicemapapp.R
import com.example.glicemapapp.ui.base.ToolbarFragment
import com.example.glicemapapp.databinding.FragmentSettingsBinding
import com.example.glicemapapp.databinding.FragmentSettingsNewDoctorBinding
import com.example.glicemapapp.databinding.FragmentSettingsPersonalBinding
import com.example.glicemapapp.ui.MainActivity
import com.example.glicemapapp.ui.home.NewMeasurementFragmentDirections
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

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
            ViewModelProvider(this).get(SettingsViewModel::class.java)

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
                createDialog()
            }
        }
    }


    private fun createDialog(){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_message_one)
        dialog.findViewById<Button>(R.id.yes).setOnClickListener {
            findNavController().navigate(NewMeasurementFragmentDirections.toHome())
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.title).text = "Médico Cadastrado!"
        dialog.findViewById<TextView>(R.id.description).text = "Seu médico agora pode acompanhar suas medições de glicemia em tempo real!"
        dialog.findViewById<ImageView>(R.id.icon).setImageDrawable(context?.getDrawable(R.drawable.ic_check_circle_black_24dp))
        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()



    }
}