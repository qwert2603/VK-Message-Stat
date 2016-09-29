package com.qwert2603.vkmessagestat.di

import com.qwert2603.vkmessagestat.Const
import com.qwert2603.vkmessagestat.vkapihelper.VkApiHelper
import com.qwert2603.vkmessagestat.vkapihelper.VkRequestSender
import dagger.Module
import dagger.Provides
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class ModelModule {

    @Provides
    @Singleton
    @Named(Const.UI_THREAD)
    internal fun provideSchedulerUI(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Singleton
    @Named(Const.IO_THREAD)
    internal fun provideSchedulerIO(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @Singleton
    internal fun mVkApiHelper(): VkApiHelper {
        return VkApiHelper()
    }

    @Provides
    @Singleton
    internal fun mVkRequestSender(): VkRequestSender {
        return VkRequestSender()
    }
}
