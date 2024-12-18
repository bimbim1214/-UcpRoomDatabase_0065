package com.example.ucp2_ujicoba.repository

import com.example.ucp2_ujicoba.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySpr {
    suspend fun insertSpr(suplier: Suplier)

    fun getAllSpr() : Flow<List<Suplier>>

    fun getSpr(id : String): Flow<Suplier>
}