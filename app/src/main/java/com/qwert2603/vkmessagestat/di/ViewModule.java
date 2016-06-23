package com.qwert2603.vkmessagestat.di;

import com.qwert2603.vkmessagestat.prelude.PreludePresenter;
import com.qwert2603.vkmessagestat.prelude.quantityinterval.QuantityIntervalAdapter;
import com.qwert2603.vkmessagestat.prelude.quantityinterval.QuantityIntervalPresenter;
import com.qwert2603.vkmessagestat.prelude.timeinterval.TimeIntervalAdapter;
import com.qwert2603.vkmessagestat.prelude.timeinterval.TimeIntervalPresenter;
import com.qwert2603.vkmessagestat.results.ResultsAdapter;
import com.qwert2603.vkmessagestat.results.ResultsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Provides
    PreludePresenter mPreludePresenter() {
        return new PreludePresenter();
    }

    @Provides
    TimeIntervalPresenter mTimeIntervalPresenter() {
        return new TimeIntervalPresenter();
    }

    @Provides
    TimeIntervalAdapter mTimeIntervalAdapter() {
        return new TimeIntervalAdapter();
    }

    @Provides
    QuantityIntervalPresenter mQuantityIntervalPresenter() {
        return new QuantityIntervalPresenter();
    }

    @Provides
    QuantityIntervalAdapter mQuantityIntervalAdapter() {
        return new QuantityIntervalAdapter();
    }

    @Provides
    ResultsPresenter mResultsPresenter() {
        return new ResultsPresenter();
    }

    @Provides
    ResultsAdapter mResultsAdapter() {
        return new ResultsAdapter();
    }

}
