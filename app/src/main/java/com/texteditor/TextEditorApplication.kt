package com.texteditor

import android.app.Application
import android.content.Context

class TextEditorApplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}