package com.example.ucp2_ujicoba.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "barang")
data class Barang(
    @PrimaryKey
    val id: String,
    val nama: String,
    val Deskripsi: String,
    val Harga: Double,
    val Stok: String,
    val NamaSuplier: String
)