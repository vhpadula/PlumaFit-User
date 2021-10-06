package com.example.glicemapapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import com.example.glicemapapp.R
import com.example.glicemapapp.databinding.FragmentDayBottomSheetBinding
import com.example.glicemapapp.databinding.FragmentMeasurementBottomSheetBinding
import com.example.glicemapapp.databinding.FragmentNewMeasurementBinding
import com.example.glicemapapp.ui.base.ToolbarFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar


class DayBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentDayBottomSheetBinding? = null
    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentDayBottomSheetBinding.inflate(inflater, container, false)
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

    }


}