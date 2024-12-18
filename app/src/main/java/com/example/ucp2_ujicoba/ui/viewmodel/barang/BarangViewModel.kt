package com.example.ucp2_ujicoba.ui.viewmodel.barang

import com.example.ucp2_ujicoba.data.entity.Barang

data class FormErrorState(
    val id: String? = null,
    val nama: String? = null,
    val Deskripsi: String? = null,
    val Harga: String? = null,
    val Stok: String? = null,
    val NamaSuplier: String? = null,
)

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