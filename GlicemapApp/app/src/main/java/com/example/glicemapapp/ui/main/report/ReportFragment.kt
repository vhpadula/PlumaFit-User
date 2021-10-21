package com.example.glicemapapp.ui.main.report

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.example.glicemapapp.data.Result
import com.example.glicemapapp.ui.base.ToolbarFragment
import com.example.glicemapapp.databinding.FragmentReportBinding
import com.example.glicemapapp.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import okio.Okio
import okio.Source
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ReportFragment : ToolbarFragment() {
    private lateinit var activity: MainActivity

    private lateinit var reportViewModel: ReportViewModel
    private var _binding: FragmentReportBinding? = null
    private lateinit var pdfUri: Uri

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
        activity = requireActivity() as MainActivity
        reportViewModel.user = activity.user!!
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
        val sdfApi = SimpleDateFormat("dd/MM/yyyy")
      val dateSetListenerStart = object: DatePickerDialog.OnDateSetListener{
          override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int){
              calendar.set(Calendar.YEAR,year)
              calendar.set(Calendar.MONTH,month)
              calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
              binding.startDateDp.text = sdf.format(calendar.time).toString()
              reportViewModel.minDate = sdfApi.format(calendar.time).toString()
          }
      }

        val dateSetListenerEnd = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int){
                calendar.set(Calendar.YEAR,year)
                calendar.set(Calendar.MONTH,month)
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                binding.endDateDp.text = sdf.format(calendar.time).toString()
                reportViewModel.maxDate = sdfApi.format(calendar.time).toString()

            }
        }
        binding.startDateDp.setOnClickListener {
            DatePickerDialog(requireContext(),dateSetListenerStart,
                 calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.endDateDp.setOnClickListener {
            DatePickerDialog(requireContext(), dateSetListenerEnd, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.newMeasurementBt.setOnClickListener {
            loadPDF()
        }
    }



    private fun loadPDF(){
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.bringToFront()
        reportViewModel.getPDF().observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.INVISIBLE
            val result = it?.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let {
                            val file = File.createTempFile("relatorio", ".pdf", activity?.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS))
                            val sink = Okio.buffer(Okio.sink(file))
                            sink.writeAll(it.source() as Source)
                            sink.close()
                            Log.v("ReportFragment", Uri.fromFile((file)).toString())
                            sharePDF(
                                FileProvider.getUriForFile(requireContext(),
                                "com.example.glicemapapp.MyFileProvider",
                                file))
                            true
                        } ?: false
                    }
                    is Result.Error -> {
                        result.exception.message?.let { it1 ->
                            Snackbar.make(
                                binding.root,
                                it1,
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                        false
                    }
                }
            }
        }
    }


    private fun sharePDF(uri: Uri){
        val share = Intent()
        share.action = Intent.ACTION_SEND
        share.type = context?.contentResolver?.getType(uri)
        share.putExtra(Intent.EXTRA_STREAM, uri)
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        val shareIntent = Intent.createChooser(share, null)
        startActivity(shareIntent)
    }

}