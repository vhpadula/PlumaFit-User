package com.example.glicemapapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.glicemapapp.R
import com.example.glicemapapp.data.Result
import com.example.glicemapapp.databinding.FragmentDayBinding
import com.google.android.material.snackbar.Snackbar


class DayFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentDayBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MeasurementsAdapter
    private val args by navArgs<com.example.glicemapapp.ui.home.DayFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentDayBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.innerToolbar)


        toolbar.title = context?.getString(R.string.measurement_title, "Medições no dia", args.dateDay, args.dateMonth, args.dateYear)
        toolbar.setTitleTextAppearance(context, R.style.Toolbar_White_Bottom)
        toolbar.subtitle = args.weekDay
        toolbar.navigationIcon = null
        setAdapter()
        loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadData() {
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.bringToFront()
        homeViewModel.loadDayMeasurementDetails(args.dateApi).observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.INVISIBLE
            val result = it?.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let {
                            adapter.loadData(it)
                            true
                        } ?: false
                    }
                    is Result.Error -> {
                        Snackbar.make(
                            binding.root,
                            result.exception.message.toString(),
                            Snackbar.LENGTH_LONG
                        ).show()
                        false
                    }
                }
            }
        }
    }

    private fun setAdapter() {
        adapter = MeasurementsAdapter(context = requireContext()) {position ->
            val sugarLevel = adapter.items[position].sugarLevel
            val insulin = adapter.items[position].insulin
            val observations = adapter.items[position].observations
            val situation = adapter.items[position].situation
            findNavController().navigate(DayFragmentDirections.toMeasurement(args.dateDay, args.dateMonth,args.dateYear,args.weekDay,situation,sugarLevel,insulin,observations))
        }
        binding.run {
            measurementsDayRv.adapter = adapter
            measurementsDayRv.layoutManager = LinearLayoutManager(requireContext())
            measurementsDayRv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}