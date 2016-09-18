package com.qwert2603.vkmessagestat

import android.content.Context
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ViewAnimator
import com.squareup.picasso.Picasso
import com.vk.sdk.api.model.VKApiUser

fun ViewGroup.inflate(layoutRes: Int, attachToRoot: Boolean = false): View
        = LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)


fun ViewAnimator.showIfNotYet(child: Int) {
    if (child != displayedChild) {
        displayedChild = child
    }
}


fun VKApiUser.getPhoto(): String = this.photo_200


fun ImageView.loadPhoto(url: String) {
    Picasso.with(context).load(url).into(this)
}

fun Context.isInternetConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected;
}