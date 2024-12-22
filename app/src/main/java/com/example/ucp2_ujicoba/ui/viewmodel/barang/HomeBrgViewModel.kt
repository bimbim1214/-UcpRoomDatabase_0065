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
    val homeUIState: StateFlow<HomeUIStateBrg> = repositoryBrg.getAllBrg()
        .filterNotNull()
        .map {
            HomeUIStateBrg(
                listBrg = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeUIStateBrg(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUIStateBrg(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUIStateBrg(
                isLoading = true,
            )
        )
}

data class HomeUIStateBrg(
    val listBrg: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)