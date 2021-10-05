package com.example.glicemapapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.example.contadormtg.models.Measurement
import com.example.glicemapapp.data.Result
import com.example.glicemapapp.databinding.FragmentHomeBinding
import com.example.glicemapapp.databinding.FragmentNewMeasurementBinding
import com.example.glicemapapp.ui.base.ToolbarFragment
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList


class NewMeasurementFragment : ToolbarFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentNewMeasurementBinding? = null
    private val binding get() = _binding!!

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}