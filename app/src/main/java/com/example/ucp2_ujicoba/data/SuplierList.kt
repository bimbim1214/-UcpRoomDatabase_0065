package com.example.ucp2_ujicoba.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_ujicoba.ui.viewmodel.PenyediaViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.suplier.HomeSprViewModel

object SuplierList {
    @Composable
    fun DataNamaSpr(
        sprHome: HomeSprViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String> {
        val getDataNamaSpr by sprHome.homeUIState.collectAsState()
        val namaSpr = getDataNamaSpr.listSpr.map { it.nama }
        return namaSpr
    }
}