package com.example.ucp2_ujicoba.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_ujicoba.data.entity.Barang
import com.example.ucp2_ujicoba.repository.RepositoryBrg
import com.example.ucp2_ujicoba.ui.navigation.DestinasiUpdateBrg
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class UpdateBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg
): ViewModel() {
    var updateUIState by mutableStateOf(BrgUIState())
        private set
    private val _id: String = checkNotNull(savedStateHandle[DestinasiUpdateBrg.ID])

    init {
        viewModelScope.launch {
            updateUIState = repositoryBrg.getBrg(_id)
                .filterNotNull()
                .first()
                .toUIStateBrg()
        }
    }


fun Barang.toUIStateBrg(): BrgUIState = BrgUIState(
    barangEvent = this.toDetailUiEvent(),
)