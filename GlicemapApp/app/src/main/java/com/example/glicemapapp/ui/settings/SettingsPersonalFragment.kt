package com.example.glicemapapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.glicemapapp.R
import com.example.glicemapapp.ui.base.ToolbarFragment
import com.example.glicemapapp.databinding.FragmentSettingsBinding
import com.example.glicemapapp.databinding.FragmentSettingsPersonalBinding
import com.example.glicemapapp.ui.MainActivity
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class SettingsPersonalFragment : ToolbarFragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    private var _binding: FragmentSettingsPersonalBinding? = null

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

        _binding = FragmentSettingsPersonalBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val activity = requireActivity() as MainActivity
       val sdf =  SimpleDateFormat("dd/MM/yyyy")
        binding.nameEt.setText(activity.user.name)
        binding.birthdayEt.setText(sdf.format(activity.user.birthdate))
        setListeners()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners(){
        binding.run{

        }
    }
}