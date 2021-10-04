package com.example.glicemapapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.example.glicemapapp.databinding.FragmentHomeBinding
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val events: MutableList<EventDay> = ArrayList()
        val calendar: Calendar = Calendar.getInstance()
        events.add(EventDay(calendar, com.example.glicemapapp.R.drawable.ic_check_circle_black_24dp))
        val calendarView: CalendarView = binding.calendarView as CalendarView
        calendarView.setEvents(events)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}