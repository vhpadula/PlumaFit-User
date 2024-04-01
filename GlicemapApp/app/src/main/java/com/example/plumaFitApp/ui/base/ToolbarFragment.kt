package com.example.plumaFitApp.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.plumaFitApp.R

abstract class ToolbarFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.innerToolbar)

        val navHostFragment = NavHostFragment.findNavController(this)
        toolbar.setNavigationOnClickListener{
          navHostFragment.navigateUp()
        }
        navHostFragment.addOnDestinationChangedListener { controller, destination, arguments ->
            toolbar.title = destination.label.toString()
            when (destination.id){
                R.id.navigation_home -> toolbar.navigationIcon = null
                R.id.navigation_gym -> toolbar.navigationIcon = null
                R.id.navigation_psico -> toolbar.navigationIcon = null
                R.id.navigation_nutri -> toolbar.navigationIcon = null
            }
        }

    }


}