package com.example.glicemapapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import com.example.glicemapapp.R
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
                homeViewModel.registerMeasurement(sugarLevel, insulin, situation, observation)
            }
        }
    }


}