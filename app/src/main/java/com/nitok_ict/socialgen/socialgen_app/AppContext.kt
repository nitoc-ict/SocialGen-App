package com.nitok_ict.socialgen.socialgen_app

import android.app.Application
import android.content.Context

class AppContext(private val applicationContext: Context) {

    companion object {
        private lateinit var instance: AppContext

        fun onCreateApplication(applicationContext: Context) {
            // Application#onCreateのタイミングでシングルトンが生成される
            instance = AppContext(applicationContext)
        }

        fun getInstance(): AppContext {
            if (instance == null) {
                // こんなことは起きないはず
                throw RuntimeException("MyContext should be initialized!")
            }
            return instance
        }

        fun getApplicationContext(): Context = instance.applicationContext
    }
}

class MyApplication : Application() {
    // Application#onCreateは、ActivityやServiceが生成される前に呼ばれる。
    // だから、ここでシングルトンを生成すれば問題ない
    override fun onCreate() {
        super.onCreate()
        AppContext.onCreateApplication(applicationContext)
    }
}