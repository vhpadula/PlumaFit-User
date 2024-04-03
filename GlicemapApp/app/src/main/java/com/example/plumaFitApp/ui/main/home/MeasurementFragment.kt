package com.example.plumaFitApp.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plumaFitApp.R
import com.example.plumaFitApp.data.Result
import com.example.plumaFitApp.data.network.handleException
import com.example.plumaFitApp.databinding.FragmentMeasurementBinding
import com.google.android.material.snackbar.Snackbar


class MeasurementFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentMeasurementBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<com.example.plumaFitApp.ui.main.home.MeasurementFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        _binding = FragmentMeasurementBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.innerToolbar)
        toolbar.title = context?.getString(R.string.measurement_title, "Medição dia", args.dateDay, args.dateMonth, args.dateYear)
        toolbar.setTitleTextAppearance(context, R.style.Toolbar_White_Bottom)
        toolbar.subtitle = args.weekDay
        val navHostFragment = NavHostFragment.findNavController(this)
        toolbar.setNavigationOnClickListener{
            navHostFragment.navigateUp()
        }

        binding.run{
//            circumstanceSp.text = args.situation
//            sugarLevelEtTv.text = context?.getString(R.string.measurement_sugar_level, args.sugarLevel)
//            insulinEtTv.text = context?.getString(R.string.measurement_insulin, args.insulin)
//            if (args.observations.isNullOrEmpty()){
//                notesEt.text = "Nenhuma"
//            } else {
//                notesEt.text = args.observations
//
//            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun setListeners() {

    }




}