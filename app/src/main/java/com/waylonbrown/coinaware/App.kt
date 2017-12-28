package com.waylonbrown.coinaware

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.waylonbrown.coinaware.dagger.AndroidModule
import com.waylonbrown.coinaware.dagger.ApplicationComponent
import com.waylonbrown.coinaware.dagger.DaggerApplicationComponent

class App : Application() {

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var graph: ApplicationComponent
    }
    
    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)

        graph = DaggerApplicationComponent
                .builder()
                .androidModule(AndroidModule(this))
                .build()
        graph.inject(this)
    }

}