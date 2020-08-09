package com.hitesh.appwithawsamplify

import android.app.Application
import android.util.Log
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        try {
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.configure(applicationContext)

            Log.d("Amplify Config", "Done")
        } catch (e: Exception) {
            Log.e("Amplify Config", "Error", e)
        }
    }
}