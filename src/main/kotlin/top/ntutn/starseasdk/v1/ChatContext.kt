package top.ntutn.starseasdk.v1

/**
 * 机器人收到消息上下文对象，可以在此取到一些相关信息或进行一些操作
 */
interface ChatContext {
    /**
     * 发送方chat_id
     */
    val chatId: String

    /**
     * 使用文本进行直接回复
     * @param text 回复内容
     */
    fun replyWithText(text: String)
}

/**
 * 文本消息上下文
 */
interface TextChatContext: ChatContext {
    /**
     * 收到的文本内容
     */
    val text: String
}