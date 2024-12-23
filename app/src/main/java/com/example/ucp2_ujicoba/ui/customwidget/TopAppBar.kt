package com.example.ucp2_ujicoba.ui.customwidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2_ujicoba.R


@Composable
fun HeaderWithAppBar(
    judulAppBar: String,
    onBack: () -> Unit,
    showBackButton: Boolean = true,
    namaUser: String = "Bimo Aditya Pangestu",
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Section Header
        SectionHeader(namaUser = namaUser)

        // App Bar
        AppBar(
            onBack = onBack,
            showBackButton = showBackButton,
            judul = judulAppBar,
            modifier = Modifier
        )
    }
}

@Composable
fun SectionHeader(namaUser: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Blue, RoundedCornerShape(bottomEnd = 50.dp))
    ) {
        Box {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    Icons.Filled.List,
                    contentDescription = "Menu",
                    Modifier.padding(end = 16.dp),
                    tint = Color.White
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(Modifier.padding(20.dp))
                Text(
                    text = "Halo,",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.padding(4.dp))
                Text(
                    text = namaUser,
                    color = Color.White
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iop),
                    contentDescription = "Profile Picture",
                    Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .shadow(10.dp, RoundedCornerShape(50.dp))
                )
            }
        }
    }
}

@Composable
fun AppBar(
    onBack: () -> Unit,
    showBackButton: Boolean = true,
    judul: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (showBackButton) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = onBack,
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text("Kembali")
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Text(
            text = judul,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
