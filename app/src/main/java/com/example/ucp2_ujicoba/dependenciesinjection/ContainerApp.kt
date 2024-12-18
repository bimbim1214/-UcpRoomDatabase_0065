package com.example.ucp2_ujicoba.dependenciesinjection

import android.content.Context
import com.example.ucp2_ujicoba.data.database.TokoDatabase
import com.example.ucp2_ujicoba.repository.LocalRepositoryBrg
import com.example.ucp2_ujicoba.repository.LocalRepositorySpr
import com.example.ucp2_ujicoba.repository.RepositoryBrg
import com.example.ucp2_ujicoba.repository.RepositorySpr

interface InterfaceContainerApp{
    val repositoryBrg : RepositoryBrg
    val repositorySpr : RepositorySpr
}

class ContainerApp (private val context: Context) : InterfaceContainerApp{
    override val repositoryBrg: RepositoryBrg by lazy {
        LocalRepositoryBrg(TokoDatabase.getDatabase(context).barangDao())
    }
    override val repositorySpr: RepositorySpr by lazy {
        LocalRepositorySpr(TokoDatabase.getDatabase(context).suplierDAo())
    }
}