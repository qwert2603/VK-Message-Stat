package com.qwert2603.vkmessagestat.di

import com.qwert2603.vkmessagestat.model.DataManager
import com.qwert2603.vkmessagestat.model.DataManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @Singleton
    internal fun mDataManager(): DataManager {
        return DataManagerImpl()
    }

}
