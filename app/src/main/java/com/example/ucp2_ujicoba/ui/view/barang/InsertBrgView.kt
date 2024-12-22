package com.example.ucp2_ujicoba.ui.view.barang

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_ujicoba.data.SuplierList
import com.example.ucp2_ujicoba.ui.customwidget.AppBar
import com.example.ucp2_ujicoba.ui.customwidget.DynamicSelectTextField
import com.example.ucp2_ujicoba.ui.viewmodel.barang.BarangEvent
import com.example.ucp2_ujicoba.ui.viewmodel.barang.BarangViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.barang.BrgUIState
import com.example.ucp2_ujicoba.ui.viewmodel.barang.FormErrorStateBrg
import com.example.ucp2_ujicoba.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch





@Composable
fun InsertBodyBrg( // Menambahkan tampilan form untuk memasukkan data barang dan button simpan.
    modifier: Modifier = Modifier,
    onValueChange: (BarangEvent) -> Unit,
    uiState: BrgUIState,
    onClick: () -> Unit
) {
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormBarang(
            barangEvent = uiState.barangEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }
    }
}

@Composable
fun FormBarang(
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = {},
    errorState: FormErrorStateBrg = FormErrorStateBrg(),
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Input untuk Nama Barang
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.id,
            onValueChange = {
                onValueChange(barangEvent.copy(id = it))
            },
            label = { Text(text = "ID") },
            isError = errorState.id != null,
            placeholder = { Text(text = "Masukkan ID") }
        )
        Text(
            text = errorState.id ?: "",
            color = Color.Red
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.nama,
            onValueChange = {
                onValueChange(barangEvent.copy(nama = it))
            },
            label = { Text(text = "Nama") },
            isError = errorState.nama != null,
            placeholder = { Text(text = "Masukkan nama") }
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.Deskripsi,
            onValueChange = {
                onValueChange(barangEvent.copy(Deskripsi = it))
            },
            label = { Text(text = "Deskripsi") },
            isError = errorState.Deskripsi != null,
            placeholder = { Text(text = "Masukkan Deskripsi") }
        )
        Text(
            text = errorState.Deskripsi ?: "",
            color = Color.Red
        )

        // Input untuk Harga Barang
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.Harga,
            onValueChange = {
                onValueChange(barangEvent.copy(Harga = it))
            },
            label = { Text(text = "Harga") },
            isError = errorState.Harga != null,
            placeholder = { Text(text = "Masukkan Harga") }
        )
        Text(
            text = errorState.Harga ?: "",
            color = Color.Red
        )

        // Input untuk Stok Barang
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.Stok,
            onValueChange = {
                onValueChange(barangEvent.copy(Stok = it))
            },
            label = { Text(text = "Stok") },
            isError = errorState.Stok != null,
            placeholder = { Text(text = "Masukkan Jumlah Stok") }
        )
        Text(
            text = errorState.Stok ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Nama Suplier")

        DynamicSelectTextField(
            modifier = Modifier,
            selectedValue = barangEvent.NamaSuplier,
            label = "Nama Suplier",
            onValueChangedEvent = { selectedSpr ->
                onValueChange(barangEvent.copy(NamaSuplier = selectedSpr))
            },
            options = SuplierList.DataNamaSpr()
        )
    }
}

