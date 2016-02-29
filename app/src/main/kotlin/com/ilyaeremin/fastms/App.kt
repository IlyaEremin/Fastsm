package com.ilyaeremin.fastms

import android.app.Application
import com.karumi.dexter.Dexter

/**
 * Created by Ilya Eremin on 21.02.2016.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Dexter.initialize(this)
    }
}
