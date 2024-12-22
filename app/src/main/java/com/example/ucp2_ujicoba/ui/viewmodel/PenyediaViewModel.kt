package com.example.ucp2_ujicoba.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2_ujicoba.TokoApp
import com.example.ucp2_ujicoba.ui.viewmodel.barang.BarangViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.barang.DetailBrgViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.barang.HomeBrgViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.barang.UpdateBrgViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.suplier.DetailSprViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.suplier.HomeSprViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.suplier.SuplierViewModel


object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            BarangViewModel(
                tokoApp().containerApp.repositoryBrg
            )
        }

        initializer {
            HomeBrgViewModel(
                tokoApp().containerApp.repositoryBrg
            )
        }
        initializer {
            DetailBrgViewModel(
                createSavedStateHandle(),
                tokoApp().containerApp.repositoryBrg
            )
        }
        initializer {
            UpdateBrgViewModel(
                createSavedStateHandle(),
                tokoApp().containerApp.repositoryBrg
            )
        }
        initializer {
            SuplierViewModel(
                tokoApp().containerApp.repositorySpr
            )
        }

        initializer {
            HomeSprViewModel(
                tokoApp().containerApp.repositorySpr
            )
        }

        initializer {
            DetailSprViewModel(
                createSavedStateHandle(),
                tokoApp().containerApp.repositorySpr
            )
        }
    }
}

fun CreationExtras.tokoApp(): TokoApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TokoApp)