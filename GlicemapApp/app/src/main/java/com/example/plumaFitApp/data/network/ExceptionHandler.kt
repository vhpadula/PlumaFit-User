package com.example.plumaFitApp.data.network

import android.app.Activity
import androidx.fragment.app.Fragment


fun Fragment.handleException(message: String): String{
    when (message){
        "PDF_EXPORT_ERROR_0001" -> return "Erro de IO - Nome/Caminho de arquivo inválido"
        "PDF_EXPORT_ERROR_0002" -> return "Erro de Documento - Falha na criação do documento"
        "SIGNUP_ERROR_0010" -> return "Cadastro com dados incompletos"
        "SIGNUP_ERROR_0002" -> return "Usuario já possui conta"
        "DELETE_MEDIC_ERROR_0001" -> return "Usuário não existe"
        "DELETE_MEDIC_ERROR_0002" -> return "Usuário não possui médico"
        "ADD_MEDIC_ERROR_0001" -> return "Usuário não existe"
        "ADD_MEDIC_ERROR_0001" -> return "Usuário não existe"
        "ADD_MEDIC_ERROR_0002" -> return "Codigo inválido"
        "ADD_MEDIC_ERROR_0003" -> return "Usuário já possui médico"
        "UPDATE_INFO_ERROR_0001" -> return "Atualização com dados incompletos"
        "UPDATE_INFO_ERROR_0002" -> return "Usuário não existe"
        else -> return "Erro de conexão"
    }

}


fun Activity.handleException(message: String): String{
    when (message){
        "PDF_EXPORT_ERROR_0001" -> return "Erro de IO - Nome/Caminho de arquivo inválido"
        "PDF_EXPORT_ERROR_0002" -> return "Erro de Documento - Falha na criação do documento"
        "SIGNUP_ERROR_0010" -> return "Cadastro com dados incompletos"
        "SIGNUP_ERROR_0002" -> return "Usuario já possui conta"
        "DELETE_MEDIC_ERROR_0001" -> return "Usuário não existe"
        "DELETE_MEDIC_ERROR_0002" -> return "Usuário não possui médico"
        "ADD_MEDIC_ERROR_0001" -> return "Usuário não existe"
        "ADD_MEDIC_ERROR_0001" -> return "Usuário não existe"
        "ADD_MEDIC_ERROR_0002" -> return "Codigo inválido"
        "ADD_MEDIC_ERROR_0003" -> return "Usuário já possui médico"
        "UPDATE_INFO_ERROR_0001" -> return "Atualização com dados incompletos"
        "UPDATE_INFO_ERROR_0002" -> return "Usuário não existe"
        else -> return "Erro de conexão"
    }







}