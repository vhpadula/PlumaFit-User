package com.example.glicemapapp.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.glicemapapp.R

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
                R.id.navigation_report -> toolbar.navigationIcon = null
                R.id.navigation_notifications -> toolbar.navigationIcon = null
                R.id.navigation_settings -> toolbar.navigationIcon = null
            }
        }

    }


}