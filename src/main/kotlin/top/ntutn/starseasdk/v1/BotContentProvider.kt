package top.ntutn.starseasdk.v1

/**
 * 机器人功能接口，需要插件来实现
 */
interface BotContentProvider {
    val pluginName: String

    /**
     * 关心的指令。如果一条命令含有特定指令，将优先发送给当前机器人处理。
     * 如： listOf("start")
     */
    val registeredCommands: List<String> get() = listOf()

    /**
     * 插件加载事件
     */
    fun onPluginLoaded() {}

    /**
     * 每分钟执行一次，可以用于执行一些周期性任务。
     */
    fun tick() {}

    /**
     * 接收到一条文本消息
     */
    @Deprecated("rename")
    fun onTextMessage(context: TextChatContext): Boolean = false

    /**
     * 接收到一条文本消息
     */
    fun onTextMessage(context: ITextChatContext): Boolean = false

    fun onPhotoMessage(context: IPhotoChatContext) = false
}