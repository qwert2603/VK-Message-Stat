package com.qwert2603.vkmessagestat.di;

import com.qwert2603.vkmessagestat.prelude.PreludeFragment;
import com.qwert2603.vkmessagestat.prelude.PreludePresenter;
import com.qwert2603.vkmessagestat.prelude.quantityinterval.QuantityIntervalViewHolder;
import com.qwert2603.vkmessagestat.prelude.timeinterval.TimeIntervalViewHolder;
import com.qwert2603.vkmessagestat.results.ResultsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        ModelModule.class,
        PresenterModule.class,
        ViewModule.class
})
public interface AppComponent {

    void inject(PreludeFragment preludeFragment);

    void inject(PreludePresenter preludePresenter);

    void inject(ResultsFragment resultsFragment);

    void inject(TimeIntervalViewHolder timeIntervalViewHolder);

    void inject(QuantityIntervalViewHolder quantityIntervalViewHolder);
}
