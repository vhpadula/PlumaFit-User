package com.example.glicemapapp.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.glicemapapp.R
import com.example.glicemapapp.data.Result
import com.example.glicemapapp.databinding.FragmentNewMeasurementBinding
import com.example.glicemapapp.ui.base.ToolbarFragment
import com.google.android.material.snackbar.Snackbar


class NewMeasurementFragment : ToolbarFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentNewMeasurementBinding? = null
    private val binding get() = _binding!!
    private lateinit var spinner: Spinner


// Create an ArrayAdapter using the string array and a default spinner layout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentNewMeasurementBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSpinner()
        setListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setSpinner() {
        spinner = binding.circumstanceSp
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.circumstances_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun setListeners() {
        binding.saveButton.setOnClickListener {
            if (binding.sugarLevelEt.text.isNullOrEmpty() || binding.insulinEt.text.isNullOrEmpty()) {
                Snackbar.make(
                    binding.root,
                    "Os campos de Glicemia e Insulina Aplicada não podem estar vazios!",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                val sugarLevel = binding.sugarLevelEt.text.toString()
                val insulin = binding.insulinEt.text.toString()
                val situation = binding.circumstanceSp.selectedItem.toString()
                val observation = binding.notesEt.text.toString()
                sendData(sugarLevel,insulin,situation,observation)
            }
        }
    }

    private fun sendData(sugarLevel: String, insulin: String, situation: String, observation: String?) {
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.bringToFront()
        homeViewModel.registerMeasurement(sugarLevel,insulin,situation,observation).observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.INVISIBLE
            val result = it?.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let {
                            createDialog()
                            true
                        } ?: false
                    }
                    is Result.Error -> {
                        Snackbar.make(
                            binding.root,
                            "Erro de conexão",
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
            findNavController().navigate(NewMeasurementFragmentDirections.toHome())
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.title).text = "Medição Registrada!"
        dialog.findViewById<TextView>(R.id.description).text = "Sua nova medição foi registrada! Você já pode visualizá-la clicando na data de hoje na tela de início"
        dialog.findViewById<ImageView>(R.id.icon).setImageDrawable(context?.getDrawable(R.drawable.ic_check_circle_black_24dp))
        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()



    }


}