package top.ntutn.starseasdk.v2

import top.ntutn.starseasdk.proxy.NewestApi
import java.io.File

/**
 * 机器人全局上下文。在插件加载后随时可用。用于主动行为，如主动向用户发送消息。
 */
@NewestApi
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

@NewestApi
interface IChatContext {
    /**
     * 发送方chat_id
     */
    val chatId: String

    /**
     * 使用文本进行直接回复
     * @param text 回复内容
     */
    fun replyWithText(text: String)

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
    fun replyWithLocalPhoto(photoFile: File)
}

/**
 * 文本消息上下文
 */
@NewestApi
interface ITextChatContext: IChatContext {
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

/**
 * 一个图片文件尺寸资源
 */
@NewestApi
interface IPhotoSize {
    val fileId: String
    val width: Int
    val height: Int
}

/**
 * 图片上下文
 */
@NewestApi
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
