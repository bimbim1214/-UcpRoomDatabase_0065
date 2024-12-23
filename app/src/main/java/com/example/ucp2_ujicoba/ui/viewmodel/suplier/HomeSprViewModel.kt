package com.example.ucp2_ujicoba.ui.viewmodel.suplier

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_ujicoba.data.entity.Suplier
import com.example.ucp2_ujicoba.repository.RepositorySpr
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeSprViewModel (
    private val repositorySpr: RepositorySpr
) :ViewModel() {
    val homeUIState: StateFlow<HomeUIStateSpr> = repositorySpr.getAllSpr()
        .filterNotNull()
        .map {
            HomeUIStateSpr(
                listSpr = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeUIStateSpr(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUIStateSpr(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = HomeUIStateSpr(
                isLoading = true,
            )
        )
}

data class HomeUIStateSpr(
    val listSpr: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)