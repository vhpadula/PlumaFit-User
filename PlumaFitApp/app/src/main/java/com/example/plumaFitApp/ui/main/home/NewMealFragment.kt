package com.example.plumaFitApp.ui.main.home

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.plumaFitApp.R
import com.example.plumaFitApp.databinding.FragmentNewMealBinding
import com.example.plumaFitApp.ui.base.ToolbarFragment
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar


class NewMealFragment : ToolbarFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentNewMealBinding? = null
    private val binding get() = _binding!!
    private val cal = Calendar.getInstance()


// Create an ArrayAdapter using the string array and a default spinner layout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        _binding = FragmentNewMealBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setTimePicker()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setTimePicker() {
        binding.timeOfDayEt.setOnClickListener {

            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                binding.timeOfDayEt.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(
                requireContext(),
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }
    }

    private fun setListeners() {
        binding.saveButton.setOnClickListener {
            if (binding.mealEt.text.isNullOrEmpty() || binding.timeOfDayEt.text.isNullOrEmpty()) {
                Snackbar.make(
                    binding.root,
                    "Os campos de Refeição e Horário não podem estar vazios!",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                val meal = binding.mealEt.text.toString()
                val timeOfDay = binding.timeOfDayEt.text.toString()
                val type = binding.typeOfMealEt.text.toString()
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
            findNavController().navigate(com.example.plumaFitApp.ui.main.home.NewEmotionFragmentDirections.toHome())
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.title).text = "Refeição Registrada!"
        dialog.findViewById<TextView>(R.id.description).text = "Sua Refeição já foi registrada! Você já pode visualizá-la clicando na data de hoje na tela de início"
        dialog.findViewById<ImageView>(R.id.icon).setImageDrawable(context?.getDrawable(R.drawable.ic_check_circle_black_24dp))
        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()



    }


}