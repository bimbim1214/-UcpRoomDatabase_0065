package com.example.ucp2_ujicoba.ui.view.barang


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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_ujicoba.data.entity.Barang
import com.example.ucp2_ujicoba.ui.customwidget.AppBar
import com.example.ucp2_ujicoba.ui.viewmodel.barang.HomeBrgViewModel
import com.example.ucp2_ujicoba.ui.viewmodel.barang.HomeUIStateBrg
import com.example.ucp2_ujicoba.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch



@Composable
fun BodyHomeBrgView(
    homeUIState: HomeUIStateBrg,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember {SnackbarHostState()}
    when{
        homeUIState.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        homeUIState.isError -> {
            LaunchedEffect(homeUIState.errorMessage) {
                homeUIState.errorMessage?.let{mesdage ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(mesdage)
                    }
                }
            }
        }
        homeUIState.listBrg.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Tidak ada data barang.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            ListBarang(
                listBrg = homeUIState.listBrg,
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
fun ListBarang(
    listBrg: List<Barang>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
){
    LazyColumn(
        modifier = modifier
    ){
        items (
            items = listBrg,
            itemContent = {
                    brg ->
                CardBrg(
                    brg = brg,
                    onClick = {onClick(brg.id)}
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardBrg(
    brg: Barang,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    // Konversi stok dari String ke Int
    val stok = brg.Stok.toIntOrNull() ?: 0 // Jika gagal parsing, default stok = 0

    // Tentukan warna berdasarkan jumlah stok
    val cardColor: Color = when {
        stok == 0 -> Color.Gray
        stok in 1..10 -> Color.Red
        else -> Color.Green
    }

    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = cardColors(containerColor = cardColor)
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
                Text(text = brg.nama, fontWeight = FontWeight.Bold, fontSize = 20.sp)
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
                Text(text = brg.id, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = " "
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = brg.Harga, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = " "
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = brg.Stok, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        }
    }
}
