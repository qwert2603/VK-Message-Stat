package com.qwert2603.vkmessagestat.util

import android.util.Log

object LogUtils {

    const val APP_TAG = "AASSDD"
    const val ERROR_MSG = "ERROR!!!"

    @JvmStatic
    fun d(s: String) {
        d(APP_TAG, s)
    }

    @JvmStatic
    fun d(tag: String, s: String) {
        Log.d(tag, s)
    }

    @JvmStatic
    fun e(t: Throwable) {
        Log.e(APP_TAG, ERROR_MSG, t)
    }

}
