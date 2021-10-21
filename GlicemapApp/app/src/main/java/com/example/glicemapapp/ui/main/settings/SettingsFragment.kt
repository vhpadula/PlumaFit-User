package com.example.glicemapapp.ui.main.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.glicemapapp.R
import com.example.glicemapapp.ui.base.ToolbarFragment
import com.example.glicemapapp.databinding.FragmentSettingsBinding
import com.example.glicemapapp.ui.main.MainActivity

class SettingsFragment : ToolbarFragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    private var _binding: FragmentSettingsBinding? = null

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

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val activity = requireActivity() as MainActivity

        if (activity.doctor!= null) {
            binding.doctorNameTv.text = activity.doctor!!.name
            binding.doctorNameTv.visibility = View.VISIBLE
            binding.doctorEmailTv.text = activity.doctor!!.email
            binding.doctorEmailTv.visibility = View.VISIBLE
            binding.doctorCrmTv.text = context?.getString(R.string.settings_doctor_crm, activity.doctor!!.documentNumber)
            binding.doctorCrmTv.visibility = View.VISIBLE
        } else {
            binding.doctorNameTv.text = "Cadastre seu Médico"
        }

        if(activity.user!=null){
            binding.personalEmailTv.visibility=View.VISIBLE
            binding.personalMinTv.visibility=View.VISIBLE
            binding.personalMaxTv.visibility=View.VISIBLE
            binding.personalNameTv.text = activity.user!!.name
            binding.personalEmailTv.text = activity.user!!.email
            binding.personalMinTv.text =
                context?.getString(R.string.settings_sugar_min_level, activity.user!!.sugarMin)
            binding.personalMaxTv.text =
                context?.getString(R.string.settings_sugar_max_level, activity.user!!.sugarMax)
        } else {
            binding.personalNameTv.text = "Sem dados do usuário"
        }



        setListeners()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        val activity = requireActivity() as MainActivity
        binding.run {
            personal.setOnClickListener {
                findNavController().navigate(com.example.glicemapapp.ui.main.settings.SettingsFragmentDirections.toPersonal())
            }

            doctor.setOnClickListener {
                if (activity.doctor != null) {
                    findNavController().navigate(com.example.glicemapapp.ui.main.settings.SettingsFragmentDirections.toDeleteDoctor())
                } else {
                    findNavController().navigate(com.example.glicemapapp.ui.main.settings.SettingsFragmentDirections.toNewDoctor())
                }

            }
        }
    }
}