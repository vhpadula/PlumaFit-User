package com.example.plumaFitApp.ui.main.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plumaFitApp.data.Result
import com.example.plumaFitApp.data.models.DietMealsResponse
import com.example.plumaFitApp.data.models.Food
import com.example.plumaFitApp.data.models.Meal
import com.example.plumaFitApp.data.network.handleException
import com.example.plumaFitApp.databinding.FragmentMealsBinding
import com.example.plumaFitApp.ui.base.ToolbarFragment
import com.example.plumaFitApp.ui.main.home.MeasurementsAdapter
import com.example.plumaFitApp.ui.main.meals.MealsViewModel
import com.google.android.material.snackbar.Snackbar

class MealsFragment : ToolbarFragment() {

    private lateinit var mealsViewModel: MealsViewModel
    private var _binding: FragmentMealsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: MealsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mealsViewModel =
            ViewModelProvider(requireActivity()).get(MealsViewModel::class.java)
        mealsViewModel.activity=requireActivity()
        mealsViewModel.setValues()

        _binding = FragmentMealsBinding.inflate(inflater, container, false)
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
    fun mockDietMealsResponse(): DietMealsResponse {
        val meals = mutableListOf(
            Meal(
                name = "Meal 1",
                foods = mutableListOf(
                    Food(name = "Food 1", quantity = "1"),
                    Food(name = "Food 2", quantity = "2")
                )
            ),
            Meal(
                name = "Meal 2",
                foods = mutableListOf(
                    Food(name = "Food 3", quantity = "3"),
                    Food(name = "Food 4", quantity = "4")
                )
            )
        )
        return DietMealsResponse(meals = meals)}

    private fun loadData() {
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.bringToFront()
        mealsViewModel.loadDietMeals().observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.INVISIBLE
            val result = it?.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let {
                            adapter.loadData(it)
                            if (it.meals.isEmpty()){
                            }
                            true
                        } ?: false
                    }
                    is Result.Error -> {
                        adapter.loadData(mockDietMealsResponse())
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
        binding.newMealButton.setOnClickListener {
            findNavController().navigate(MealsFragmentDirections.toNewMeal())
        }
    }

    private fun setAdapter() {
        adapter = MealsAdapter(context = requireContext())
        binding.run {
            notificationsRv.adapter = adapter
            notificationsRv.layoutManager = LinearLayoutManager(requireContext())
            notificationsRv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}