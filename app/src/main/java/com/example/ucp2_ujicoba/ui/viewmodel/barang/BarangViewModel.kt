package com.example.ucp2_ujicoba.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_ujicoba.data.entity.Barang
import com.example.ucp2_ujicoba.repository.RepositoryBrg
import kotlinx.coroutines.launch



    fun saveDataBrg(){
        val currentEvent = uiBrgstate.barangEvent
        if (validateField()) {
            viewModelScope.launch {
                try {
                    repositoryBrg.insertBrg(currentEvent.toBarangEntity())
                    uiBrgstate = uiBrgstate.copy(
                        snackbarMessage = "Data berhasil disimpan",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormErrorStateBrg()
                    )
                }catch (e: Exception) {
                    uiBrgstate = uiBrgstate.copy(
                        snackbarMessage = "Data gagal disimpan"
                    )
                }
            }
        }else{
            uiBrgstate = uiBrgstate.copy(
                snackbarMessage = "input tidak valid, periksa lagi data anda"
            )
        }
    }

    fun resertSnackBarMessage(){
        uiBrgstate = uiBrgstate.copy(snackbarMessage = null)
    }

}

data class BrgUIState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorStateBrg = FormErrorStateBrg(),
    val snackbarMessage: String? = null,

)

data class FormErrorStateBrg(
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