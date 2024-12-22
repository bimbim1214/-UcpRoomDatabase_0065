package com.example.ucp2_ujicoba.ui.view.barang

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_ujicoba.data.entity.Barang
import com.example.ucp2_ujicoba.ui.customwidget.AppBar
import com.example.ucp2_ujicoba.ui.viewmodel.PenyediaViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.barang.DetailBrgViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.barang.DetailUIState
import com.example.ucp2_ujicoba.ui.viewmodel.barang.toBarangEntity
import com.example.ucp2_ujicoba.ui.viewmodel.suplier.DetailUiState



@Composable
fun ItemDetailBrg(
    modifier: Modifier = Modifier,
    barang: Barang

){
    Card (
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailBrg(judul = "ID", isinya = barang.id)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBrg(judul = "Nama", isinya = barang.nama)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBrg(judul = "Deskripsi", isinya = barang.Deskripsi)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBrg(judul = "Harga", isinya = barang.Harga)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBrg(judul = "Stok", isinya = barang.Stok.toString())
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailBrg(judul = "Nama Suplier", isinya = barang.NamaSuplier)
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}


@Composable
fun ComponentDetailBrg(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String

){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCencel: () -> Unit,
    modifier: Modifier = Modifier

){
    AlertDialog(onDismissRequest = { /*TODO*/ },
        title = { Text( "Delete Data") },
        text = { Text("Apakah anda yakin ingin mengahpus data?" ) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCencel) {
                Text(text = "Cencel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}