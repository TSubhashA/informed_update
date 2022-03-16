package com.informed.evaluator.common

import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.StrictMode




private lateinit var application : BaseApp

class BaseApp : Application(){

    companion object{
        fun getAppContext() = application
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks{
            override fun onActivityCreated(p0: Activity, p1: Bundle?) {

                p0.requestedOrientation=ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
            }

            override fun onActivityStarted(p0: Activity) {

            }

            override fun onActivityResumed(p0: Activity) {

            }

            override fun onActivityPaused(p0: Activity) {
                          }

            override fun onActivityStopped(p0: Activity) {

            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

            }

            override fun onActivityDestroyed(p0: Activity) {

            }
        })
        application = this
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)


    }

}