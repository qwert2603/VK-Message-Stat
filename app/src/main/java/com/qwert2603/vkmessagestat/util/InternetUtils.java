package com.qwert2603.vkmessagestat.util;

import android.content.Context;
import android.net.ConnectivityManager;

public final class InternetUtils {

    /**
     * Проверить наличие подключения к интернету.
     */
    public static boolean isInternetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}