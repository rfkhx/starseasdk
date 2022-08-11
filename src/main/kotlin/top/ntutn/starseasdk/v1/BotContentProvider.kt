package top.ntutn.starseasdk.v1

/**
 * 机器人功能接口，需要插件来实现
 */
interface BotContentProvider {
    val pluginName: String

    /**
     * 插件加载事件
     */
    fun onPluginLoaded() {}

    fun onTextMessage(context: TextChatContext): Boolean = false
}