package com.qwert2603.vkmessagestat.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import com.qwert2603.vkmessagestat.R
import com.qwert2603.vkmessagestat.prelude.PreludeActivity
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError

class LoginActivity : AppCompatActivity() {

    companion object {
        val LOGIN_SCOPE = arrayOf(VKScope.FRIENDS, VKScope.MESSAGES)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        VKSdk.login(this, *LOGIN_SCOPE)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
            override fun onResult(res: VKAccessToken) {
                startActivity(Intent(this@LoginActivity, PreludeActivity::class.java))
                finish()
            }

            override fun onError(error: VKError) {
                Toast.makeText(this@LoginActivity, R.string.login_failed, Toast.LENGTH_SHORT).show()
                VKSdk.login(this@LoginActivity, *LOGIN_SCOPE)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}