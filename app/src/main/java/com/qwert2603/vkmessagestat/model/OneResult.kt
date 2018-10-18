package com.qwert2603.vkmessagestat.model

data class OneResult(
        val resultInfo: ResultInfo,
        val percent: Double,
        val quantity: Int
) : Identifiable {
    override fun getId(): Int = resultInfo.id.toInt()

    data class ResultInfo(
            val id: Long,
            val name: String,
            val photoUrl: String
    ) {
        fun canClick() = id in 1 until 1_000_000_000
    }
}