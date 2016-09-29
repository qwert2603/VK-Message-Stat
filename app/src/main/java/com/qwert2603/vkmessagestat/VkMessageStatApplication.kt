package com.qwert2603.vkmessagestat

import android.app.Application
import com.qwert2603.vkmessagestat.di.AppComponent
import com.qwert2603.vkmessagestat.di.AppModule
import com.qwert2603.vkmessagestat.di.DaggerAppComponent
import com.qwert2603.vkmessagestat.util.LogUtils
import com.vk.sdk.VKSdk
import com.vk.sdk.util.VKUtil

class VkMessageStatApplication : Application() {

    companion object {
        lateinit var sAppComponent: AppComponent

        fun getAppComponent() = sAppComponent
    }


    override fun onCreate() {
        super.onCreate()

        sAppComponent = buildAppComponent()

        VKSdk.initialize(this@VkMessageStatApplication)

        //LogUtils.d(VKAccessToken.currentToken().accessToken)

        for (s in VKUtil.getCertificateFingerprint(this@VkMessageStatApplication, this@VkMessageStatApplication.packageName)) {
            LogUtils.d("CertificateFingerprint", "CertificateFingerprint == " + s);
        }
    }

    fun buildAppComponent(): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(this@VkMessageStatApplication))
                    .build()
}