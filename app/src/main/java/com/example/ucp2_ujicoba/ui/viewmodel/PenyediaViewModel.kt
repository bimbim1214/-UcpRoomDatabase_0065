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



fun CreationExtras.tokoApp(): TokoApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TokoApp)