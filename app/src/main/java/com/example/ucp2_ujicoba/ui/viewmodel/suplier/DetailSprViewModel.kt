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


fun Suplier.toDetailUiEvent(): SuplierEvent{
    return SuplierEvent(
        id = id,
        nama = nama,
        kontak = kontak,
        alamat = alamat
    )
}