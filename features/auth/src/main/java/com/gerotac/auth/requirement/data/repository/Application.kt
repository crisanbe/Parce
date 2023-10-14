package com.gerotac.auth.requirement.data.repository

import android.annotation.SuppressLint
import android.content.Context

class Application {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}