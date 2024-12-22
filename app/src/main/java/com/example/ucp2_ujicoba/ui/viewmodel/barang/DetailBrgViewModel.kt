package com.example.ucp2_ujicoba.ui.viewmodel.barang

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_ujicoba.data.entity.Barang
import com.example.ucp2_ujicoba.repository.RepositoryBrg
import com.example.ucp2_ujicoba.ui.navigation.DestinasiDetailBrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class DetailBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg,
) : ViewModel() {
    private val _id: String = checkNotNull(savedStateHandle[DestinasiDetailBrg.ID])

    val detailUiState: StateFlow<DetailUIState> = repositoryBrg.getBrg(_id)
        .filterNotNull()
        .map {
            DetailUIState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUIState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUIState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUIState(
                isLoading = true,
            )
        )

    fun deleteBrg() {
        detailUiState.value.detailUiEvent.toBarangEntity().let {
            viewModelScope.launch {
                repositoryBrg.deleteBrg(it)
            }
        }
    }
}

data class DetailUIState(
    val detailUiEvent: BarangEvent = BarangEvent(),
    val isLoading:Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUieventEmpty: Boolean
        get() = detailUiEvent == BarangEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != BarangEvent()
}

fun Barang.toDetailUiEvent(): BarangEvent{
    return BarangEvent(
        id = id,
        nama = nama,
        Deskripsi = Deskripsi,
        Harga = Harga,
        Stok = Stok,
        NamaSuplier = NamaSuplier
    )
}

