package top.ntutn.starseasdk.v2

import java.util.ServiceLoader

/**
 * 用于访问机器人的全局上下文。
 */
object BotContext: IBotContext by ServiceLoader.load(IBotContext::class.java).first()
