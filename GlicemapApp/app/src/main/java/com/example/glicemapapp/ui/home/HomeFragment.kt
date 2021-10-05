package com.example.glicemapapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.example.contadormtg.models.Measurement
import com.example.glicemapapp.data.Result
import com.example.glicemapapp.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    val measurementMock = listOf(
        Measurement("01-10-2021","131","2", "antes do almoço", "enjoado"),
        Measurement("01-10-2021","100","1", "antes do lanche da tarde", ""),
        Measurement("02-10-2021","98","2", "depois do jantar", "coloquei um alarme para daqui 2 horas"),
        Measurement("02-10-2021","80","1", "depois do almoço", ""),
        Measurement("02-10-2021","121","1", "antes de dormir", ""),
        Measurement("03-10-2021","141","2", "depois de acordar", "glicemia muito alta"),
        Measurement("04-10-2021","90","2", "depois do café da manhã", ""),
        Measurement("04-10-2021","89","1", "outra", "medi depois de comer um lanche de manhã"),
        Measurement("05-10-2021","110","3", "outra", "medi depois de fazer uma caminhada"),
        Measurement("05-10-2021","120","1", "antes de dormir", ""),
        Measurement("05-10-2021","105","2", "antes do almoço", ""))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setListeners()
//        loadMeasurements()
        setCalendar(homeViewModel.setCalendarData(measurementMock))
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadMeasurements(){
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.bringToFront()
        homeViewModel.loadData().observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.INVISIBLE
            val result = it?.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let { measurements ->
                            homeViewModel.setCalendarData(measurements)
                            true
                        } ?: false
                    }
                    is Result.Error -> {
                        Snackbar.make(
                            binding.root,
                            result.exception.message.toString(),
                            Snackbar.LENGTH_SHORT
                        ).show()
                        false
                    }
                }
            }
        }
    }

    private fun setCalendar(events: List<EventDay>) {
        val calendar: Calendar = Calendar.getInstance()
        val calendarView: CalendarView = binding.calendarView as CalendarView
        calendarView.setEvents(events)
        calendarView.setDate(calendar)
    }

    private fun setListeners(){
        binding.newMeasurementBt.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toNewMeasurement())
        }
    }

}