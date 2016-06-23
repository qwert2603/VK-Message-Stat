package com.qwert2603.vkmessagestat.prelude;

import com.qwert2603.vkmessagestat.base.BaseView;
import com.qwert2603.vkmessagestat.model.QuantityInterval;
import com.qwert2603.vkmessagestat.model.TimeInterval;

import java.util.List;

public interface PreludeView extends BaseView {
    void showTimes(List<TimeInterval> times);
    void showQuantities(List<QuantityInterval> quantities);
}
