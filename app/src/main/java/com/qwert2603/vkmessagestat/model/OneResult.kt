package com.qwert2603.vkmessagestat.model

import com.vk.sdk.api.model.VKApiUserFull

data class OneResult(
        var user: VKApiUserFull,
        val percent: Double,
        val quantity: Int
) : Identifiable {
    override fun getId() = user.id
}
