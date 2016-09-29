package com.qwert2603.vkmessagestat.results

import android.content.Context
import com.qwert2603.vkmessagestat.VkMessageStatApplication
import com.qwert2603.vkmessagestat.base.BasePresenter
import com.qwert2603.vkmessagestat.isInternetConnected
import com.qwert2603.vkmessagestat.model.DataManager
import com.qwert2603.vkmessagestat.model.Results
import com.qwert2603.vkmessagestat.util.LogUtils
import javax.inject.Inject

class ResultsPresenter : BasePresenter<Results, ResultsView>() {

    @Inject
    lateinit var appContext: Context

    @Inject
    lateinit var dataManager: DataManager

    private var error = false

    private var calculating = false

    init {
        VkMessageStatApplication.getAppComponent().inject(this)
    }

    fun setInterval(intervalType: IntervalType, value: Int) {
        model = Results(intervalType, value, 0, 0, emptyList())
        loadStatistic()
    }

    override fun onUpdateView(view: ResultsView) {
        if (error) {
            view.showLayer(Layer.LOADING_ERROR)
            return
        }
        if (model != null) {
            view.showTitle(model.interval().toString(appContext))
            if (calculating) {
                view.showLayer(Layer.CALCULATING)
                view.showDone(model.done)
                view.showTotal(model.interval().toString(appContext))
                view.showProgress(model.progress())
            } else {
                if (!model.resultsList.isEmpty()) {
                    view.showLayer(Layer.RESULTS)
                    view.showResultList(model.resultsList)
                } else {
                    view.showLayer(Layer.NO_MESSAGES)
                }
            }
        }
    }

    fun onReloadClicked() {
        error = !appContext.isInternetConnected()
        updateView()
        if (!error) {
            loadStatistic()
            updateView()
        } else {
            view.showNoInternet()
        }
    }

    fun loadStatistic() {
        calculating = true
        val stats = dataManager.getMessageStatistic(model.intervalType, model.value)
        val subscription1 = stats.first
                .subscribe(
                        {
                            calculating = false
                            model = it
                        },
                        { ex ->
                            error = true
                            calculating = false
                            updateView()
                            LogUtils.e(ex)
                        }
                )
        mCompositeSubscription.add(subscription1)

        val subscription2 = stats.second
                .subscribe(
                        {
                            if (model != null) {
                                model.done = it.done
                                model.total = it.total
                                updateView()
                            }
                        },
                        { LogUtils.e(it) }
                )
        mCompositeSubscription.add(subscription2)
    }
}
