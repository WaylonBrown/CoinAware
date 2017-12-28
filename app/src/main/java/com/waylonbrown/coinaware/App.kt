package com.waylonbrown.coinaware

import android.app.Application
import android.util.Log
import com.squareup.leakcanary.LeakCanary
import com.waylonbrown.coinaware.dagger.AndroidModule
import com.waylonbrown.coinaware.dagger.ApplicationComponent
import com.waylonbrown.coinaware.dagger.DaggerApplicationComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

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

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(ProdTree())
        }

        graph = DaggerApplicationComponent
                .builder()
                .androidModule(AndroidModule(this))
                .build()
        graph.inject(this)
    }

    /** A tree which logs important information for crash reporting.  */
    private class ProdTree : Timber.Tree() {
        
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }
        }
    }
}