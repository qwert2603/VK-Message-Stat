package com.qwert2603.vkmessagestat.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val mAppContext: Context) {

    @Provides
    @Singleton
    internal fun mContext(): Context {
        return mAppContext
    }

}
