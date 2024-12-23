package com.example.ucp2_ujicoba.ui.view.suplier

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_ujicoba.data.entity.Suplier
import com.example.ucp2_ujicoba.ui.customwidget.HeaderWithAppBar
import com.example.ucp2_ujicoba.ui.viewmodel.PenyediaViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.suplier.DetailSprViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.suplier.DetailUiState
import com.example.ucp2_ujicoba.ui.viewmodel.suplier.toSuplierEntity



@Composable
fun BodyDetailSpr(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState = DetailUiState(),
    onDeleteClick: () -> Unit = { }
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false)}
    when{
        detailUiState.isLoading -> {
            Box (
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        detailUiState.isUieventEmpty -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                ItemDetailSpr(
                    suplier = detailUiState.detailUiEvent.toSuplierEntity(),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(onClick = {
                    deleteConfirmationRequired = true
                },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Delete")
                }
                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCencel = { deleteConfirmationRequired = false},
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        detailUiState.isUieventEmpty -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ItemDetailSpr(
    modifier: Modifier = Modifier,
    suplier: Suplier
){
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailSpr(judul = "ID", isinya = suplier.id)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailSpr(judul = "NAMA", isinya = suplier.nama)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailSpr(judul = "ALAMAT", isinya = suplier.alamat)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailSpr(judul = "KONTAK", isinya = suplier.kontak)
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}


@Composable
fun ComponentDetailSpr(
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
        title = { Text( "Delete Data")},
        text = { Text("Apakah anda yakin ingin mengahpus data?" )},
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