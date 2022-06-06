package com.example.constraintdemo

import android.app.Application
import com.example.constraintdemo.data.api.RetrofitClient
import com.example.constraintdemo.data.repository.AppRepository
import com.example.constraintdemo.ui.authentication.factory.UserViewmodelFactory
import com.example.constraintdemo.ui.home.factory.HomeviewmodelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class Application : Application(), KodeinAware {
    override fun onCreate() {
        super.onCreate()
        application = this
        }

    companion object {
        @JvmStatic
        var application: Application? = null
            private set
    }
    //kodein injection provide by kotlin
    override val kodein = Kodein.lazy {
        import(androidXModule(this@Application))
        bind() from singleton { RetrofitClient() }
        bind() from provider {
            UserViewmodelFactory(
                instance(), appRepository = AppRepository()
            )
        }
        bind() from provider {
            HomeviewmodelFactory(
                instance(), appRepository = AppRepository()
            )
        }

    }
    }