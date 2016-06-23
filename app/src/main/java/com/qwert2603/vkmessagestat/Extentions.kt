package com.qwert2603.vkmessagestat

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ViewAnimator
import com.squareup.picasso.Picasso
import com.vk.sdk.api.model.VKApiUser

fun ViewGroup.inflate(layoutRes: Int, attachToRoot: Boolean = false)
        = LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)


fun ViewAnimator.showIfNotYet(child: Int) {
    if (child != displayedChild) {
        displayedChild = child
    }
}


fun VKApiUser.getPhoto() = this.photo_200


fun ImageView.loadPhoto(url: String) {
    Picasso.with(context).load(url).into(this)
}