package com.example.glicemapapp.ui.main.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.glicemapapp.data.models.Notification

class NotificationsViewModel : ViewModel() {

    var items = mutableListOf<Notification>( Notification("Pós Hora Extra", "05:00", " Dom Qua Sáb "),
        Notification("Após Almoço", "13:00", "Todos os Dias"),
        Notification("Antes da Festa", "08:00", "Só uma vez"),
        Notification("Depois da Aula", "17:00", "Dias de Semana"))
}