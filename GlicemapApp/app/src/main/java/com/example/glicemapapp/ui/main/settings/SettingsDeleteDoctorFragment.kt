package com.example.glicemapapp.ui.main.settings

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.glicemapapp.R
import com.example.glicemapapp.ui.base.ToolbarFragment
import com.example.glicemapapp.databinding.FragmentSettingsDeleteDoctorBinding

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
            dialog.dismiss()
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
}