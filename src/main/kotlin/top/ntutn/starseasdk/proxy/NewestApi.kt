package top.ntutn.starseasdk.proxy

/**
 * 标记当前类是最新API版本的类，用于自动生成typealias
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class NewestApi
