package com.example.plumaFitApp.ui.main.settings

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.plumaFitApp.R
import com.example.plumaFitApp.data.Result
import com.example.plumaFitApp.data.models.SignUpRequest
import com.example.plumaFitApp.data.network.handleException
import com.example.plumaFitApp.databinding.FragmentSettingsPersonalBinding
import com.example.plumaFitApp.ui.base.DateInputMask
import com.example.plumaFitApp.ui.base.ToolbarFragment
import com.example.plumaFitApp.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import kotlin.math.roundToInt

class SettingsPersonalFragment : ToolbarFragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    private var _binding: FragmentSettingsPersonalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProvider(requireActivity()).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsPersonalBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val activity = requireActivity() as MainActivity
        settingsViewModel.user = activity.user!!
        val sdfApi = SimpleDateFormat("yyyy-MM-dd")
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        binding.nameEt.setText(activity.user?.name)
        binding.surnameEt.setText(activity.user?.lastName)
        val date =   sdfApi.parse(activity.user?.birthdate)
        binding.birthdayEt.setText(
            sdf.format(date)
        )
        binding.heightEt.setText(activity.user?.height)
        binding.weightEt.setText(activity.user?.weight)
        binding.minEt.setText(activity.user?.sugarMin)
        binding.maxEt.setText(activity.user?.sugarMax)
        binding.emailEt.setText(activity.user?.email)
        setListeners()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        val mask = DateInputMask(binding.birthdayEt).listen()
        binding.run {
            saveButton.setOnClickListener {
                if(!binding.passwordEt.text.isNullOrEmpty()){
                    if(binding.passwordEt.text.toString() == binding.newPasswordEt.text.toString()){
                        val sdfApi = SimpleDateFormat("yyyy-MM-dd")
                        val sdf = SimpleDateFormat("dd/MM/yyyy")
                        val date =   sdf.parse(birthdayEt.text.toString())
                        val birthdate = sdfApi.format(date)

                        alterData(SignUpRequest(
                            birthdate = birthdate,
                            crmMedic = "",
                            documentNumber = settingsViewModel.user.documentNumber,
                            email = emailEt.text.toString(),
                            height = heightEt.text.toString().toInt(),
                            lastName = surnameEt.text.toString(),
                            name = nameEt.text.toString(),
                            password = passwordEt.text.toString(),
                            sugarMax = maxEt.text.toString().toInt(),
                            sugarMin = minEt.text.toString().toInt(),
                            weight = weightEt.text.toString().toFloat().roundToInt()
                        ) )
                    } else {
                        Snackbar.make(
                            binding.root,
                            "'Confirmar Nova Senha' deve conter a mesma senha que 'Nova Senha'",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                } else {
                    val sdfApi = SimpleDateFormat("yyyy-MM-dd")
                    val sdf = SimpleDateFormat("dd/MM/yyyy")
                    val date =   sdf.parse(birthdayEt.text.toString())
                    val birthdate = sdfApi.format(date)

                    alterData(SignUpRequest(
                        birthdate = birthdate,
                        crmMedic = "",
                        documentNumber = settingsViewModel.user.documentNumber,
                        email = emailEt.text.toString(),
                        height = heightEt.text.toString().toInt(),
                        lastName = surnameEt.text.toString(),
                        name = nameEt.text.toString(),
                        password = settingsViewModel.user.password,
                        sugarMax = maxEt.text.toString().toInt(),
                        sugarMin = minEt.text.toString().toInt(),
                        weight = weightEt.text.toString().toFloat().roundToInt()
                    ) )
                }


            }
        }
    }

    private fun alterData(request: SignUpRequest){
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.bringToFront()
        settingsViewModel.alterData(request).observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.INVISIBLE
            val result = it?.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let {
                            if (it){
                                createDialog()
                            } else {
                                Snackbar.make(
                                    binding.root,
                                    "Houve um erro no cadastro, tente novamente mais tarde",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }

                            true
                        } ?: false
                    }
                    is Result.Error -> {
                        Snackbar.make(
                            binding.root,
                            handleException(result.exception.message.toString()),
                            Snackbar.LENGTH_LONG
                        ).show()
                        false
                    }
                }
            }
        }
    }


    private fun createDialog(){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_message_one)
        dialog.findViewById<Button>(R.id.yes).setOnClickListener {
            dialog.dismiss()
            val i = Intent(
                this.context,
                MainActivity::class.java
            )
            i.putExtra("document", settingsViewModel.user.documentNumber)
            startActivity(i)
        }
        dialog.findViewById<TextView>(R.id.title).text = "Dados alterados com sucesso!"
        dialog.findViewById<TextView>(R.id.description).text = "As mudanças no seu cadastro já foram alteradas no sistema."
        dialog.findViewById<ImageView>(R.id.icon).setImageDrawable(context?.getDrawable(R.drawable.ic_check_circle_black_24dp))
        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()



    }
}