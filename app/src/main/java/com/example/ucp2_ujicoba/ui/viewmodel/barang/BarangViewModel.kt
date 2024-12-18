package com.example.ucp2_ujicoba.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucp2_ujicoba.data.entity.Barang
import com.example.ucp2_ujicoba.repository.RepositoryBrg

class BarangViewModel (private val repositoryBrg: RepositoryBrg) : ViewModel() {
    var uiState by mutableStateOf(BrgUIState())

    fun updateState(barangEvent: BarangEvent){
        uiState = uiState.copy(
            barangEvent = barangEvent,
        )
    }

    private fun validateField(): Boolean {
        val event = uiState.barangEvent
        val errorState = FormErrorState(
            id = if (event.id.isNotEmpty()) null else "ID tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            Deskripsi = if (event.Deskripsi.isNotEmpty()) null else "Deskripsi tidak boleh kosong",
            Harga = if (event.Harga.isNotEmpty()) null else "Harga tidak boleh kosong",
            Stok = if (event.Stok.isNotEmpty()) null else " Stok tidak boleh kosong",
            NamaSuplier = if (event.NamaSuplier.isNotEmpty()) null else "NamaSuplier tidak boleh kosong",
        )
        uiState = uiState.copy(
            isEtryValid = errorState
        )
        return errorState.isValid()
    }
}

data class BrgUIState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEtryValid: FormErrorState = FormErrorState(),
    val snackbarMessage: String? = null,
)

data class FormErrorState(
    val id: String? = null,
    val nama: String? = null,
    val Deskripsi: String? = null,
    val Harga: String? = null,
    val Stok: String? = null,
    val NamaSuplier: String? = null,
){
    fun isValid(): Boolean{
        return id == null && nama == null && Deskripsi == null && Harga == null && Stok == null && NamaSuplier == null
    }
}

fun BarangEvent.toBarangEntity(): Barang = Barang(
    id = id,
    nama = nama,
    Deskripsi = Deskripsi,
    Harga = Harga,
    Stok = Stok,
    NamaSuplier = NamaSuplier
)
data class BarangEvent(
    val id: String = "",
    val nama: String = "",
    val Deskripsi: String = "",
    val Harga: String = "",
    val Stok: String = "",
    val NamaSuplier: String = ""
)