package com.nyt.mostpopular

import com.dastanapps.dastanlib.DastanLibApp

/**
 * Created by dastaniqbal on 25/11/2018.
 * 25/11/2018 7:44
 */
open class App : DastanLibApp() {
    private val TAG = this::class.java.simpleName
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: App
    }
}