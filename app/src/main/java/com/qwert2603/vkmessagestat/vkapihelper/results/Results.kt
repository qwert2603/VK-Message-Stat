package com.qwert2603.vkmessagestat.vkapihelper.results

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