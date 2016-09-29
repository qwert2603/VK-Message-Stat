package com.qwert2603.vkmessagestat.di

import com.qwert2603.vkmessagestat.model.DataManagerImpl
import com.qwert2603.vkmessagestat.oneresult.OneResultViewHolder
import com.qwert2603.vkmessagestat.prelude.PreludeFragment
import com.qwert2603.vkmessagestat.prelude.PreludePresenter
import com.qwert2603.vkmessagestat.prelude.quantityinterval.QuantityIntervalViewHolder
import com.qwert2603.vkmessagestat.prelude.timeinterval.TimeIntervalViewHolder
import com.qwert2603.vkmessagestat.results.ResultsFragment
import com.qwert2603.vkmessagestat.results.ResultsPresenter
import com.qwert2603.vkmessagestat.vkapihelper.VkApiHelper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        ModelModule::class,
        PresenterModule::class,
        ViewModule::class
))
interface AppComponent {

    fun inject(preludeFragment: PreludeFragment)

    fun inject(preludePresenter: PreludePresenter)

    fun inject(resultsFragment: ResultsFragment)

    fun inject(timeIntervalViewHolder: TimeIntervalViewHolder)

    fun inject(quantityIntervalViewHolder: QuantityIntervalViewHolder)

    fun inject(resultsPresenter: ResultsPresenter)

    fun inject(dataManagerImpl: DataManagerImpl)

    fun inject(oneResultViewHolder: OneResultViewHolder)

    fun inject(vkApiHelper: VkApiHelper)
}
