package com.example.ucp2_ujicoba

import android.app.Application
import com.example.ucp2_ujicoba.dependenciesinjection.ContainerApp

class TokoApp : Application() {
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()

        containerApp = ContainerApp(this)
    }
}