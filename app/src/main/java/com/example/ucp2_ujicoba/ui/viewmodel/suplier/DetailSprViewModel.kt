package com.example.ucp2_ujicoba.ui.viewmodel.suplier

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_ujicoba.data.entity.Suplier
import com.example.ucp2_ujicoba.repository.RepositorySpr
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

class DetailSprViewModel(
    savedStateHandle: SavedStateHandle,
    private  val repositorySpr: RepositorySpr,
) : ViewModel() {
    private val _id: String = checkNotNull(savedStateHandle[DestinasiDetailBrg.ID])

    val detailUiState: StateFlow<DetailUiState> = repositorySpr.getSpr(_id)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalah"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = DetailUiState(
                isLoading = true,
            )
        )
}

data class DetailUiState(
    val detailUiEvent: SuplierEvent = SuplierEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUieventEmpty: Boolean
        get() = detailUiEvent == SuplierEvent()

    val isUieventNotEmpty: Boolean
        get() = detailUiEvent != SuplierEvent()
}

fun Suplier.toDetailUiEvent(): SuplierEvent{
    return SuplierEvent(
        id = id,
        nama = nama,
        kontak = kontak,
        alamat = alamat
    )
}