package com.qwert2603.vkmessagestat.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.qwert2603.vkmessagestat.prelude.PreludeActivity
import com.vk.sdk.VKSdk

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent: Intent
        if (VKSdk.isLoggedIn()) {
            intent = Intent(this@MainActivity, PreludeActivity::class.java)
        } else {
            intent = Intent(this@MainActivity, LoginActivity::class.java)
        }
        startActivity(intent)
        finish()
    }

}