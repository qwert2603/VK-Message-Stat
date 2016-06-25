package com.qwert2603.vkmessagestat.results

import android.content.Context
import com.qwert2603.vkmessagestat.VkMessageStatApplication
import com.qwert2603.vkmessagestat.base.BasePresenter
import com.qwert2603.vkmessagestat.isInternetConnected
import com.qwert2603.vkmessagestat.mock.Mocks
import com.qwert2603.vkmessagestat.model.DataManager
import com.qwert2603.vkmessagestat.model.QuantityInterval
import com.qwert2603.vkmessagestat.model.Results
import com.qwert2603.vkmessagestat.model.TimeInterval
import com.qwert2603.vkmessagestat.util.LogUtils
import javax.inject.Inject

class ResultsPresenter : BasePresenter<Results, ResultsView>() {

    @Inject @JvmField
    var appContext : Context = Mocks.MOCK_CONTEXT

    @Inject @JvmField
    var dataManager : DataManager = Mocks.MOCK_DATA_MANAGER

    var error = false

    override fun bindView(view: ResultsView?) {
        super.bindView(view)
        if (appContext == Mocks.MOCK_CONTEXT) {
            VkMessageStatApplication.getAppComponent().inject(this)
        }
    }

    fun setInterval(intervalType: IntervalType, value: Int) {
        model = Results(intervalType, value, -1, -1, emptyList())
        loadStatistic()
    }

    override fun onUpdateView(view: ResultsView) {
        if (error) {
            view.showLayer(Layer.LOADING_ERROR)
            return
        }
        if (model != null) {
            when(model.intervalType) {
                IntervalType.TIME -> view.showTitle(TimeInterval(model.value).toString(appContext))
                IntervalType.QUANTITY -> view.showTitle(QuantityInterval(model.value).toString(appContext))
            }
            if (model.done < model.total || model.total < 0) {
                view.showLayer(Layer.CALCULATING)
                view.showDone(model.done)
                view.showTotal(model.total)
                view.showProgress(model.done * 100 / model.total)
            } else {
                view.showLayer(Layer.RESULTS)
                view.showResultList(model.resultsList)
            }
        }
    }

    fun onReloadClicked() {
        error = !appContext.isInternetConnected()
        updateView()
        if (!error) {
            if (model != null) {
                loadStatistic()
            }
        } else {
            view.showNoInternet()
        }
    }

    fun loadStatistic() {
        val subscription = dataManager.getMessageStatistic(model.intervalType, model.value)
                .subscribe (
                        { results -> model = results },
                        { ex ->
                            error = true
                            updateView()
                            LogUtils.e(ex)
                        }
                )
        mCompositeSubscription.add(subscription)
    }
}
