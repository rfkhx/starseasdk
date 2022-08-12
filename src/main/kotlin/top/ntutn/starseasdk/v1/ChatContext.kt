package top.ntutn.starseasdk.v1

import java.io.File

/**
 * 机器人全局上下文。在插件加载后随时可用。用于主动行为，如主动向用户发送消息。
 */
interface IBotContext {
    /**
     * 向其他用户发送文本内容
     */
    fun sendMessage(chatId: String, text: String)

    /**
     * 向其他用户发送图片文件，图片要求已经上传到tg的服务器
     * @param photoFileId 图片id
     */
    fun sendCloudPhoto(chatId: String, photoFileId: String)

    /**
     * 向其他用户发送图片文件，图片要求存放在公网可访问的服务器
     * @param photoUrl 图片地址，要求设置正确的mimetype。大小限制10M，宽高不能超过10000，宽高比不能超过20。
     */
    fun sendRemotePhoto(chatId: String, photoUrl: String)

    /**
     * 向其他用户发送本地图片
     * @param photoFile 本地图片文件。大小限制10M，宽高不能超过10000，宽高比不能超过20。
     */
    fun sendLocalPhoto(chatId: String, photoFile: File)
}

/**
 * 机器人收到消息上下文对象，可以在此取到一些相关信息或进行一些操作
 */
@Deprecated("rename", ReplaceWith("IChatContext"))
interface ChatContext: IBotContext {
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

interface IChatContext: ChatContext {
    /**
     * 使用已经上传到tg的文件回复
     * @see IBotContext.sendCloudPhoto
     */
    fun replyWithCloudPhoto(photoFileId: String)

    /**
     * 使用远程服务器上的图片回复
     * @see IBotContext.sendRemotePhoto
     */
    fun replyWithRemotePhoto(photoUrl: String)

    /**
     * 使用本地图片进行回复
     * @see IBotContext.sendLocalPhoto
     */
    fun replyWithLocalPhoto(photoUrl: File)
}

/**
 * 文本消息上下文
 */
@Deprecated("ITextChatContext")
interface TextChatContext: ChatContext {
    /**
     * 收到的文本内容
     */
    val text: String

    /**
     * 如果用户输入的是一条指令，则command是指令内容。否则为null。
     * 如/help得到的是help。
     */
    val command: String?

    /**
     * 如果用户输入的是一条指令，则command是指令内容。
     */
    val params: List<String>?
}

interface ITextChatContext: TextChatContext

/**
 * 一个图片文件尺寸资源
 */
interface IPhotoSize {
    val fileId: String
    val width: Int
    val height: Int
}

/**
 * 图片上下文
 */
interface IPhotoChatContext: IChatContext {
    /**
     * 文本内容，如果有
     */
    val text: String?

    /**
     * 对话中用到的单个图片资源的不同资源尺寸
     */
    val photoSizes: List<IPhotoSize>
}
