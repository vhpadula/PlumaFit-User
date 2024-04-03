package com.example.plumaFitApp.ui.main.psico

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plumaFitApp.data.Result
import com.example.plumaFitApp.data.models.GetMedicinesResponse
import com.example.plumaFitApp.data.models.Medicine
import com.example.plumaFitApp.data.network.handleException
import com.example.plumaFitApp.databinding.FragmentPsicoBinding
import com.example.plumaFitApp.ui.base.ToolbarFragment
import com.example.plumaFitApp.ui.main.psico.MedicineAdapter
import com.example.plumaFitApp.ui.main.psico.MedicineViewModel
import com.google.android.material.snackbar.Snackbar

class MedicineFragment : ToolbarFragment() {

    private lateinit var medicineViewModel: MedicineViewModel
    private var _binding: FragmentPsicoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: MedicineAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        medicineViewModel =
            ViewModelProvider(requireActivity()).get(MedicineViewModel::class.java)
        medicineViewModel.activity=requireActivity()
        medicineViewModel.setValues()

        _binding = FragmentPsicoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setAdapter()
        loadData()
        setListeners()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun mockGetMedicinesResponse(): GetMedicinesResponse {
        return GetMedicinesResponse(
            medicines = listOf(
                Medicine(name = "Medicine 1", instructions = "Take once a day"),
                Medicine(name = "Medicine 2", instructions = "Take twice a day"),
                // Add more medicines as needed
            )
        )
    }
    private fun loadData() {
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.bringToFront()
        medicineViewModel.loadMedicines().observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.INVISIBLE
            val result = it?.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let {
                            adapter.loadData(it)
                            if (it.medicines.isEmpty()){
                            }
                            true
                        } ?: false
                    }
                    is Result.Error -> {
                        adapter.loadData(mockGetMedicinesResponse())
//                        Snackbar.make(
//                            binding.root,
//                            handleException(result.exception.message.toString()),
//                            Snackbar.LENGTH_LONG
//                        ).show()
                        false
                    }
                }
            }
        }
    }

    private fun setListeners(){
        binding.newEmotionButton.setOnClickListener {
            findNavController().navigate(MedicineFragmentDirections.toNewEmotion())
        }
    }

    private fun setAdapter() {
        adapter = MedicineAdapter(context = requireContext())
        binding.run {
            notificationsRv.adapter = adapter
            notificationsRv.layoutManager = LinearLayoutManager(requireContext())
            notificationsRv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}