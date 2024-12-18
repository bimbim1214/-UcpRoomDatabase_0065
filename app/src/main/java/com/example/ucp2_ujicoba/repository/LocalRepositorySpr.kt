package com.example.ucp2_ujicoba.repository

import com.example.ucp2_ujicoba.data.dao.SuplierDao
import com.example.ucp2_ujicoba.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySpr (
    private val suplierDao: SuplierDao
) : RepositorySpr{
    override suspend fun insertSpr(suplier: Suplier) {
        suplierDao.insertSuplier(suplier)
    }

    override fun getAllSpr(): Flow<List<Suplier>> {
        return suplierDao.getAllSuplier()
    }

    override fun getSpr(id: String): Flow<Suplier> {
        return suplierDao.getSuplier(id)
    }

}