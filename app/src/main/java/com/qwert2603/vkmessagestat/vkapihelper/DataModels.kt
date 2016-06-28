package com.qwert2603.vkmessagestat.vkapihelper

import com.qwert2603.vkmessagestat.model.IntegerCountMap

data class LastMessageIdAndTime(
        val lastMessageId: Int,
        val time: Int
)

data class StartInfo(
        val start: Int, // id сообщения, с котоорго начинается статистика.
        val argsCount: Int   // кол-во аргументов в запросе (сотен сообщений для статистики).
)

data class Stats(
        val statsMap: IntegerCountMap,
        val time: Int   // время самого позднего сообщения.
)

data class Progress(
        val done: Int,
        val total: Int
)
