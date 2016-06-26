package com.qwert2603.vkmessagestat;

import android.app.Application;

import com.qwert2603.vkmessagestat.di.AppComponent;
import com.qwert2603.vkmessagestat.di.AppModule;
import com.qwert2603.vkmessagestat.di.DaggerAppComponent;
import com.vk.sdk.VKSdk;

public class VkMessageStatApplication extends Application {

    private static AppComponent sAppComponent;

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = buildAppComponent();

        VKSdk.initialize(VkMessageStatApplication.this);
        /*for (String s : VKUtil.getCertificateFingerprint(VkMessageStatApplication.this,
                VkMessageStatApplication.this.getPackageName())) {
            LogUtils.d("CertificateFingerprint", "CertificateFingerprint == " + s);
        }*/
    }

    protected AppComponent buildAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(VkMessageStatApplication.this))
                .build();
    }
}
