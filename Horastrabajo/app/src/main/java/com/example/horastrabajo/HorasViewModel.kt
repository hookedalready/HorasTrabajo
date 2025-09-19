package com.example.horastrabajo.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.horastrabajo.Activity
import com.example.horastrabajo.SheetClient

class HorasViewModel : ViewModel() {
    private val _actividades = mutableStateOf<List<Activity>>(emptyList())
    val actividades: State<List<Activity>> = _actividades

    init {
        cargarDatos()
    }

    private fun cargarDatos() {
        SheetClient.fetchData(
            onResult = { datos -> _actividades.value = datos },
            onError = { error -> println("Error al cargar datos: ${error.message}") }
        )
    }
}