package com.example.plumaFitApp.ui.main.settings

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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plumaFitApp.R
import com.example.plumaFitApp.ui.base.ToolbarFragment
import com.example.plumaFitApp.databinding.FragmentSettingsBinding
import com.example.plumaFitApp.ui.login.LoginActivity
import com.example.plumaFitApp.ui.main.MainActivity
import com.example.plumaFitApp.ui.main.psico.MedicineAdapter

class SettingsFragment : ToolbarFragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: ProfessionalsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProvider(requireActivity()).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val activity = requireActivity() as MainActivity
        settingsViewModel.user = activity.user!!


        setAdapter()

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

            newProfessionalBt.setOnClickListener {
                findNavController().navigate(com.example.plumaFitApp.ui.main.settings.SettingsFragmentDirections.toNewDoctor())
            }

            logoutBt.setOnClickListener {
                val preferences =
                    requireContext().getSharedPreferences("login", Context.MODE_PRIVATE)
                val editor = preferences.edit()
                editor.putString("remember", "false")
                editor.putString("document","")
                editor.apply()
                val i = Intent(
                    requireContext(),
                    LoginActivity::class.java
                )
                startActivity(i)
            }
        }
    }

    private fun setAdapter() {
        adapter = ProfessionalsAdapter(context = requireContext())

        binding.run {
            professionalsRv.adapter = adapter
            professionalsRv.layoutManager = LinearLayoutManager(requireContext())
            professionalsRv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }



    private fun createDialog(){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_message_two_red)
        dialog.findViewById<Button>(R.id.yes).setOnClickListener {
//            deleteDoctor()
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