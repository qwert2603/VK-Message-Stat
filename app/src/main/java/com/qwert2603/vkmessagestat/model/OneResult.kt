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
        fun pageUrl() = when {
            id > 2_000_000_000 -> "https://vk.com/im?sel=c${id - 2_000_000_000}"
            id < 0 -> "http://vk.com/club${-1 * id}"
            else -> "http://vk.com/id$id"
        }
    }
}