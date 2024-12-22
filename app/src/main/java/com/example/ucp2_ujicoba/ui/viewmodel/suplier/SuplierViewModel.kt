package com.example.ucp2_ujicoba.ui.viewmodel.suplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_ujicoba.data.entity.Suplier
import com.example.ucp2_ujicoba.repository.RepositorySpr
import com.example.ucp2_ujicoba.ui.viewmodel.barang.BarangEvent
import kotlinx.coroutines.launch

class SuplierViewModel (private val repositorySpr: RepositorySpr) : ViewModel() {
    var uiState by mutableStateOf(SprUIState())


    fun updateState(suplierEvent: SuplierEvent){
        uiState = uiState.copy(
            suplierEvent = suplierEvent,
        )
    }

    private fun validateField(): Boolean {
        val event = uiState.suplierEvent
        val errorState = FormErrorStateSpr(
            id = if (event.id.isNotEmpty()) null else "id tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "nama tidak boleh kosong",
            kontak = if (event.kontak.isNotEmpty()) null else "kkontak tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "alamat tidak boleh kosong",
        )
        uiState = uiState.copy(
            isEntryValid = errorState
        )
        return errorState.isValid()
    }


data class SprUIState(
    val suplierEvent: SuplierEvent = SuplierEvent(),
    val isEntryValid: FormErrorStateSpr = FormErrorStateSpr(),
    val snackBarMessage: String? = null,
)

data class FormErrorStateSpr(
    val id: String? = null,
    val nama: String? = null,
    val kontak: String? = null,
    val alamat: String? = null,
){
    fun isValid(): Boolean{
        return id == null && nama == null && kontak == null && alamat == null
    }
}

fun SuplierEvent.toSuplierEntity(): Suplier = Suplier(
    id = id,
    nama = nama,
    kontak = kontak,
    alamat = alamat
)

data class SuplierEvent(
    val id: String = "",
    val nama: String = "",
    val kontak: String = "",
    val alamat: String = "",
)