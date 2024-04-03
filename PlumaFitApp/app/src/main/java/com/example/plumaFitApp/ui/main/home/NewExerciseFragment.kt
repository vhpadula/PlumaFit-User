package com.example.plumaFitApp.ui.main.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.plumaFitApp.R
import com.example.plumaFitApp.databinding.FragmentNewExerciseBinding
import com.example.plumaFitApp.ui.base.ToolbarFragment
import com.google.android.material.snackbar.Snackbar


class NewExerciseFragment : ToolbarFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentNewExerciseBinding? = null
    private val binding get() = _binding!!


// Create an ArrayAdapter using the string array and a default spinner layout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        _binding = FragmentNewExerciseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        binding.saveButton.setOnClickListener {
            if (binding.exerciseEt.text.isNullOrEmpty() || binding.durationEt.text.isNullOrEmpty()) {
                Snackbar.make(
                    binding.root,
                    "Os campos de Exercício e Duração não podem estar vazios!",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                val exercise = binding.exerciseEt.text.toString()
                val duration = binding.durationEt.text.toString()
                val calories = binding.caloriesEt.text.toString()
                val observation = binding.notesEt.text.toString()
//                sendData(sugarLevel,insulin,observation)
                createDialog()
            }
        }
    }

    private fun sendData(sugarLevel: String, insulin: String, situation: String, observation: String?) {
        createDialog()
//        binding.progressBar.visibility = View.VISIBLE
//        binding.progressBar.bringToFront()
//        homeViewModel.registerMeasurement(sugarLevel,insulin,situation,observation).observe(viewLifecycleOwner){
//            binding.progressBar.visibility = View.INVISIBLE
//            val result = it?.let { result ->
//                when (result) {
//                    is Result.Success -> {
//                        result.data?.let {
//                            createDialog()
//                            true
//                        } ?: false
//                    }
//                    is Result.Error -> {
//                        Snackbar.make(
//                            binding.root,
//                            handleException(result.exception.message.toString()),
//                            Snackbar.LENGTH_LONG
//                        ).show()
//                        false
//                    }
//                }
//            }
//        }
    }

    private fun createDialog(){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_message_one)
        dialog.findViewById<Button>(R.id.yes).setOnClickListener {
            findNavController().navigate(com.example.plumaFitApp.ui.main.home.NewExerciseFragmentDirections.toHome())
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.title).text = "Exercício Registrado!"
        dialog.findViewById<TextView>(R.id.description).text = "Seu exercício já foi registrado! Você já pode visualizá-lo clicando na data de hoje na tela de início"
        dialog.findViewById<ImageView>(R.id.icon).setImageDrawable(context?.getDrawable(R.drawable.ic_check_circle_black_24dp))
        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()



    }


}