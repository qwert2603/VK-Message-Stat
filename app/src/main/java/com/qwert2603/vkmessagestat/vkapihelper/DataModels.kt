package com.qwert2603.vkmessagestat.vkapihelper

data class LastMessageIdAndTime(
        val lastMessageId: Int,
        val time: Int
)

data class StartInfo(
        val start: Int, // id сообщения, с котоорго начинается статистика.
        val argsCount: Int   // кол-во аргументов в запросе (сотен сообщений для статистики).
)
