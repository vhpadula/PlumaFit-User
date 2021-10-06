package com.example.glicemapapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.example.glicemapapp.R
import com.example.glicemapapp.databinding.FragmentDayBinding
import com.example.glicemapapp.ui.base.ToolbarFragment


class DayFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentDayBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DayFragmentArgs>()




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


        toolbar.title= args.date
        toolbar.subtitle= args.weekDay
        toolbar.navigationIcon = null
        setListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun setListeners() {

    }


}