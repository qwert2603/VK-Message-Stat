package com.qwert2603.vkmessagestat.model

data class OneResult(
        val id: Long,
        val name: String,
        val photoUrl: String,
        val percent: Double,
        val quantity: Int
) : Identifiable {
    override fun getId(): Int = id.toInt()
}