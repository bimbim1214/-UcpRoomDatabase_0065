package com.example.ucp2_ujicoba.ui.viewmodel.barang

import com.example.ucp2_ujicoba.data.entity.Barang

fun BarangEvent.toBarangEntity(): Barang = Barang(
    id = id,
    nama = nama,
    Deskripsi = Deskripsi,
    Harga = Harga,
    Stok = Stok,
    NamaSuplier = NamaSuplier
)
data class BarangEvent(
    val id: String = "",
    val nama: String = "",
    val Deskripsi: String = "",
    val Harga: String = "",
    val Stok: String = "",
    val NamaSuplier: String = ""
)