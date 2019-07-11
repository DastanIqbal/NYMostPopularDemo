package com.nyt.mostpopular

import com.dastanapps.dastanlib.DastanLibApp

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