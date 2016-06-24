package com.qwert2603.vkmessagestat.di;

import com.qwert2603.vkmessagestat.model.DataManager;
import com.qwert2603.vkmessagestat.model.DataManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    DataManager mDataManager() {
        return new DataManagerImpl();
    }

}
