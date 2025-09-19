package com.example.horastrabajo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.horastrabajo.Activity
import com.example.horastrabajo.viewmodel.HorasViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HorasScreen(
    modifier: Modifier = Modifier,
    viewModel: HorasViewModel = viewModel()
) {
    val actividades by viewModel.actividades

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(actividades) { actividad ->
            ActivityCard(actividad)
        }
    }
}

@Composable
fun ActivityCard(actividad: Activity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("🕒 ${actividad.start} → ${actividad.end}", style = MaterialTheme.typography.labelMedium)
            Spacer(Modifier.height(4.dp))
            Text(actividad.detail, style = MaterialTheme.typography.bodyLarge)
        }
    }
}