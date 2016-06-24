package com.qwert2603.vkmessagestat.results

import android.content.Context
import com.qwert2603.vkmessagestat.VkMessageStatApplication
import com.qwert2603.vkmessagestat.base.BasePresenter
import com.qwert2603.vkmessagestat.mock.MockContext
import com.qwert2603.vkmessagestat.model.DataManager
import com.qwert2603.vkmessagestat.model.DataManagerImpl
import com.qwert2603.vkmessagestat.model.Results
import com.qwert2603.vkmessagestat.util.InternetUtils
import com.qwert2603.vkmessagestat.util.LogUtils
import javax.inject.Inject

class ResultsPresenter : BasePresenter<Results, ResultsView>() {

    init {
        VkMessageStatApplication.getAppComponent().inject(this)
    }

    @Inject @JvmField
    var appContext: Context = MockContext()

    @Inject @JvmField
    var dataManager : DataManager = DataManagerImpl() //todo: use mock

    var error = false

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
        error = !InternetUtils.isInternetConnected(appContext)
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
        LogUtils.d(appContext.toString())
        LogUtils.d(dataManager.toString())
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
