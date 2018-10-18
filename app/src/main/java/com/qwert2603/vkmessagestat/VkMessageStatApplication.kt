package com.qwert2603.vkmessagestat

import android.app.Application
import com.qwert2603.vkmessagestat.di.AppComponent
import com.qwert2603.vkmessagestat.di.AppModule
import com.qwert2603.vkmessagestat.di.DaggerAppComponent
import com.vk.sdk.VKSdk

class VkMessageStatApplication : Application() {

    companion object {
        lateinit var sAppComponent: AppComponent

        fun getAppComponent() = sAppComponent
    }


    override fun onCreate() {
        super.onCreate()

        sAppComponent = buildAppComponent()

        VKSdk.customInitialize(this, resources.getInteger(R.integer.com_vk_sdk_AppId), "5.85")

//        LogUtils.d(VKAccessToken.currentToken().accessToken)

//        for (s in VKUtil.getCertificateFingerprint(this, this.packageName)) {
//            LogUtils.d("CertificateFingerprint", "CertificateFingerprint == $s");
//        }
    }

    fun buildAppComponent(): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(this@VkMessageStatApplication))
                    .build()
}