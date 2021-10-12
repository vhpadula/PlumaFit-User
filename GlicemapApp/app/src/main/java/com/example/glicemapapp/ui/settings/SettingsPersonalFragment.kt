package com.example.glicemapapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.glicemapapp.R
import com.example.glicemapapp.ui.base.ToolbarFragment
import com.example.glicemapapp.databinding.FragmentSettingsBinding
import com.example.glicemapapp.databinding.FragmentSettingsPersonalBinding
import com.example.glicemapapp.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
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
        val sdfApi = SimpleDateFormat("yyyy-MM-dd")
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        binding.nameEt.setText(activity.user?.name)
        val date =   sdfApi.parse(activity.user?.birthdate)
        binding.birthdayEt.setText(
            sdf.format(date)
        )
        binding.heightEt.setText(activity.user?.height)
        binding.weightEt.setText(activity.user?.weight)
        binding.minEt.setText(activity.user?.sugarMin)
        binding.maxEt.setText(activity.user?.sugarMax)
        binding.emailEt.setText(activity.user?.email)
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
                if(!binding.passwordEt.text.isNullOrEmpty()){
                    if(binding.passwordEt.text == binding.newPasswordEt.text ){
                        findNavController().navigateUp()
                    } else {
                        Snackbar.make(
                            binding.root,
                            "'Confirmar Nova Senha' deve conter a mesma senha que 'Nova Senha'",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }


            }
        }
    }
}