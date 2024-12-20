package com.example.ucp2_ujicoba.ui.viewmodel.barang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_ujicoba.data.entity.Barang
import com.example.ucp2_ujicoba.repository.RepositoryBrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import java.lang.Error

class HomeBrgViewModel(
    private val repositoryBrg: RepositoryBrg
) : ViewModel() {
    val homeUIState: StateFlow<HomeUIState> = repositoryBrg.getAllBrg()
        .filterNotNull()
        .map {
            HomeUIState(
                listBrg = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeUIState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUIState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUIState(
                isLoading = true,
            )
        )
}

data class HomeUIState(
    val listBrg: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)