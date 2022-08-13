package top.ntutn.starseasdk.v2

import top.ntutn.starseasdk.proxy.NewestApi
import java.util.ServiceLoader

/**
 * 用于访问机器人的全局上下文。
 */
@NewestApi
object BotContext: IBotContext by ServiceLoader.load(IBotContext::class.java).first()
