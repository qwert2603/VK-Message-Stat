package com.qwert2603.vkmessagestat.model

import com.vk.sdk.api.model.VKApiUser

data class OneResult(
        var user: VKApiUser,
        val percent: Double,
        val quantity: Int
) : Identifiable {
    override fun getId() = user.id
}
