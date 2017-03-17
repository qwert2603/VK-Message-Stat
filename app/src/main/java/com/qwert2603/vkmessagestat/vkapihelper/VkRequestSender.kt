package com.qwert2603.vkmessagestat.vkapihelper

import android.os.Handler
import android.os.Looper
import android.os.SystemClock

import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKRequest

class VkRequestSender {

    companion object {

        /**
         * Задержка перед следующим запросом.
         * Чтобы запросы не посылались слишком часто. (Не больше 3 в секунду).
         */
        @JvmField val NEXT_REQUEST_DELAY: Long = 370
    }

    /**
     * Время, когда можно посылать следующий запрос.
     */
    private var nextRequestTime: Long = 0

    init {
        nextRequestTime = SystemClock.uptimeMillis()
    }

    /**
     * Обрботчик отправки запросов
     */
    private val mHandler = Handler(Looper.getMainLooper())

    /**
     * Отправить запрос.
     * Запросы выполняются последовательно.
     * Переданный запрос будет отправлен когда придет его время.
     */
    @Synchronized fun sendRequest(request: VKRequest, listener: VKRequest.VKRequestListener) {
        if (!VKSdk.isLoggedIn()) {
            return
        }
        if (nextRequestTime <= SystemClock.uptimeMillis()) {
            request.executeWithListener(listener)
            nextRequestTime = SystemClock.uptimeMillis()
        } else {
            mHandler.postAtTime({
                if (VKSdk.isLoggedIn()) {
                    request.executeWithListener(listener)
                }
            }, nextRequestTime)
        }
        nextRequestTime += NEXT_REQUEST_DELAY
    }

}
