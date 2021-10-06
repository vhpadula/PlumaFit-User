package com.example.glicemapapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.glicemapapp.R
import com.example.glicemapapp.R.id.nav_host_fragment_bottom_sheet
import com.example.glicemapapp.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment : BottomSheetDialogFragment() {
    var dateApi = ""
    var dateDay = ""
    var dateMonth = ""
    var dateYear = ""
    var weekDay = ""
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val fragmentContainer = binding.root.findViewById<View>(nav_host_fragment_bottom_sheet)
        val navController = Navigation.findNavController(fragmentContainer)
        val bundle = Bundle()
        bundle.putString("dateDay",dateDay)
        bundle.putString("dateMonth",dateMonth)
        bundle.putString("dateYear",dateYear)
        bundle.putString("weekDay",weekDay)
        bundle.putString("dateApi",dateApi)
        navController?.setGraph(R.navigation.nav_fragment_bottom_sheet,bundle)
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}