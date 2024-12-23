package com.example.ucp2_ujicoba.ui.view.suplier

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_ujicoba.data.entity.Barang
import com.example.ucp2_ujicoba.data.entity.Suplier
import com.example.ucp2_ujicoba.ui.customwidget.AppBar
import com.example.ucp2_ujicoba.ui.viewmodel.PenyediaViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.barang.HomeBrgViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.barang.HomeUIStateBrg
import com.example.ucp2_ujicoba.ui.viewmodel.suplier.HomeSprViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.suplier.HomeUIStateSpr
import kotlinx.coroutines.launch

@Composable
fun HomeSprView(
    viewModel: HomeSprViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddSpr: () -> Unit = { },
    onDetailClick: (String) -> Unit = { },
    modifier: Modifier = Modifier,
    onBack: () ->Unit
){
    Scaffold(
        topBar = {
            AppBar(
                judul = "Daftar Suplier",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddSpr,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Suplier",
                )
            }
        }
    ) {innerPadding ->
        val homeUIStateSpr by viewModel.homeUIState.collectAsState()

        BodyHomeSprView(
            homeUIStateSpr = homeUIStateSpr,
            onClick = {onDetailClick(it)},
            modifier = Modifier.padding(innerPadding)
        )

    }
}

@Composable
fun BodyHomeSprView(
    homeUIStateSpr: HomeUIStateSpr,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when{
        homeUIStateSpr.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        homeUIStateSpr.isError -> {
            LaunchedEffect(homeUIStateSpr.errorMessage) {
                homeUIStateSpr.errorMessage?.let{mesdage ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(mesdage)
                    }
                }
            }
        }
        homeUIStateSpr.listSpr.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Tidak ada data suplier.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            ListSuplier(
                listSpr = homeUIStateSpr.listSpr,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListSuplier(
    listSpr: List<Suplier>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
){
    LazyColumn(
        modifier = modifier
    ){
        items (
            items = listSpr,
            itemContent = {
                    spr ->
                CardSpr(
                    spr = spr,
                    onClick = {onClick(spr.id)}
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSpr(
    spr: Suplier,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
){
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = " "
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = spr.id, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription = " "
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = spr.nama, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = " "
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = spr.alamat, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = " "
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = spr.kontak, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        }
    }
}