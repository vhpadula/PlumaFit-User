package com.example.glicemapapp.ui.report

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import com.example.glicemapapp.R
import com.example.glicemapapp.ui.base.ToolbarFragment
import com.example.glicemapapp.databinding.FragmentReportBinding
import java.text.SimpleDateFormat
import java.util.*

class ReportFragment : ToolbarFragment() {

    private lateinit var reportViewModel: ReportViewModel
    private var _binding: FragmentReportBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        reportViewModel =
            ViewModelProvider(this).get(ReportViewModel::class.java)
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setDatePickers()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setDatePickers(){
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd/MM/yyyy")
      val dateSetListenerStart = object: DatePickerDialog.OnDateSetListener{
          override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int){
              calendar.set(Calendar.YEAR,year)
              calendar.set(Calendar.MONTH,month)
              calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
              binding.startDateDp.text = sdf.format(calendar.time).toString()
          }
      }

        val dateSetListenerEnd = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int){
                calendar.set(Calendar.YEAR,year)
                calendar.set(Calendar.MONTH,month)
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                binding.endDateDp.text = sdf.format(calendar.time).toString()
            }
        }
        binding.startDateDp.setOnClickListener {
            DatePickerDialog(requireContext(),dateSetListenerStart,
                 calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.endDateDp.setOnClickListener {
            DatePickerDialog(requireContext(), dateSetListenerEnd, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
}