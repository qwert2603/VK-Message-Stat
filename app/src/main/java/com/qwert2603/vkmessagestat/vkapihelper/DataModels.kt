package com.qwert2603.vkmessagestat.vkapihelper

import com.qwert2603.vkmessagestat.model.IntegerCountMap

data class LastMessageIdAndTime(
        val lastMessageId: Int,
        val time: Int
)

data class StartInfo(
        val start: Int, // id сообщения, с котоорго начинается статистика.
        val argsCount: Int, // кол-во аргументов в запросе (сотен сообщений для статистики).
        val messagesPerLastArg: Int // кол-во сообщений в последнем агрументе.
)

data class Stats(
        val statsMap: IntegerCountMap,
        val time: Int   // время самого позднего сообщения.
)

data class Progress(
        val done: Int,
        val total: Int
)

data class GetChatsResponse(
        val response: List<ChatResponse>
)

data class ChatResponse(
        val id: Long,
        val title: String,
        val photo_200: String?
) {
    fun getPhotoUrl() = photo_200 ?: "https://vk.com/images/icons/im_multichat_200.png"
}

data class GetGroupsResponse(
        val response: List<GroupResponse>
)

data class GroupResponse(
        val id: Long,
        val name: String,
        val photo_200: String?
) {
    fun getPhotoUrl() = photo_200 ?: "https://vk.com/images/community_200.png"
}