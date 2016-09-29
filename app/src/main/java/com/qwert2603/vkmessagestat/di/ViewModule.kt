package com.qwert2603.vkmessagestat.di

import com.qwert2603.vkmessagestat.oneresult.OneResultPresenter
import com.qwert2603.vkmessagestat.prelude.PreludePresenter
import com.qwert2603.vkmessagestat.prelude.quantityinterval.QuantityIntervalAdapter
import com.qwert2603.vkmessagestat.prelude.quantityinterval.QuantityIntervalPresenter
import com.qwert2603.vkmessagestat.prelude.timeinterval.TimeIntervalAdapter
import com.qwert2603.vkmessagestat.prelude.timeinterval.TimeIntervalPresenter
import com.qwert2603.vkmessagestat.results.ResultsAdapter
import com.qwert2603.vkmessagestat.results.ResultsPresenter

import dagger.Module
import dagger.Provides

@Module
class ViewModule {

    @Provides
    internal fun mPreludePresenter(): PreludePresenter {
        return PreludePresenter()
    }

    @Provides
    internal fun mTimeIntervalPresenter(): TimeIntervalPresenter {
        return TimeIntervalPresenter()
    }

    @Provides
    internal fun mTimeIntervalAdapter(): TimeIntervalAdapter {
        return TimeIntervalAdapter()
    }

    @Provides
    internal fun mQuantityIntervalPresenter(): QuantityIntervalPresenter {
        return QuantityIntervalPresenter()
    }

    @Provides
    internal fun mQuantityIntervalAdapter(): QuantityIntervalAdapter {
        return QuantityIntervalAdapter()
    }

    @Provides
    internal fun mResultsPresenter(): ResultsPresenter {
        return ResultsPresenter()
    }

    @Provides
    internal fun mResultsAdapter(): ResultsAdapter {
        return ResultsAdapter()
    }

    @Provides
    internal fun mOneResultPresenter(): OneResultPresenter {
        return OneResultPresenter()
    }

}
