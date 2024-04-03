package com.example.plumaFitApp.ui.main.gym

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plumaFitApp.data.Result
import com.example.plumaFitApp.data.models.PhysicalActivitiesResponse
import com.example.plumaFitApp.data.models.PhysicalActivity
import com.example.plumaFitApp.data.network.handleException
import com.example.plumaFitApp.databinding.FragmentGymBinding
import com.example.plumaFitApp.databinding.FragmentMealsBinding
import com.example.plumaFitApp.ui.base.ToolbarFragment
import com.example.plumaFitApp.ui.main.meals.MealsViewModel
import com.google.android.material.snackbar.Snackbar

class GymFragment : ToolbarFragment() {

    private lateinit var gymViewModel: GymViewModel
    private var _binding: FragmentGymBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: GymAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gymViewModel =
            ViewModelProvider(requireActivity()).get(GymViewModel::class.java)
        gymViewModel.activity=requireActivity()
        gymViewModel.setValues()

        _binding = FragmentGymBinding.inflate(inflater, container, false)
        val root: View = binding.root
        loadData()
        setAdapter()

//        binding.newExerciseButton.setOnClickListener {
////            findNavController().navigate(com.example.plumaFitApp.ui.main.notifications.NotificationsFragmentDirections.toNewNotification())
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadData() {
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.bringToFront()
        gymViewModel.loadPhysicalActivities().observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.INVISIBLE
            val result = it?.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let {
                            adapter.loadData(it)
                            if (it.exercises.isEmpty()){
//                                binding.noMeasures.visibility= View.VISIBLE
                            }
                            true
                        } ?: false
                    }
                    is Result.Error -> {
                        adapter.loadData(mockPhysicalActivitiesResponse())
                        Snackbar.make(
                            binding.root,
                            handleException(result.exception.message.toString()),
                            Snackbar.LENGTH_LONG
                        ).show()
//                        binding.noMeasures.visibility= View.VISIBLE
                        false
                    }
                }
            }
        }
    }

    fun mockPhysicalActivitiesResponse(): PhysicalActivitiesResponse {
        val activities = mutableListOf(
            PhysicalActivity(
                name = "Activity 1",
                reps_min = 10,
                reps_max = 15,
                series = 3,
                observation = "Observation 1"
            ),
            PhysicalActivity(
                name = "Activity 2",
                reps_min = 5,
                reps_max = 10,
                series = 2,
                observation = "Observation 2"
            )
        )
        return PhysicalActivitiesResponse(exercises = activities)
    }

    private fun setAdapter() {
        adapter = GymAdapter(context = requireContext())
        binding.run {
            notificationsRv.adapter = adapter
            notificationsRv.layoutManager = LinearLayoutManager(requireContext())
            notificationsRv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}