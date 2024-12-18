package com.example.ucp2_ujicoba.repository

import com.example.ucp2_ujicoba.data.dao.BarangDao
import com.example.ucp2_ujicoba.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepositoryBrg(
    private val barangDao: BarangDao
) :RepositoryBrg {
    override suspend fun insertBrg(barang: Barang) {
        barangDao.insertBarang(barang)
    }

    override fun getAllBrg(): Flow<List<Barang>> {
        return barangDao.getAllBarang()
    }

    override fun getBrg(id: String): Flow<Barang> {
        return barangDao.getBarang(id)
    }

    override suspend fun deleteBrg(barang: Barang) {
        barangDao.deleteBarang(barang)
    }

    override suspend fun updateBrg(barang: Barang) {
        barangDao.updateBarang(barang)
    }
}