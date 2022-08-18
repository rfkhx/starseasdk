package top.ntutn.starseasdk.proxy

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import kotlin.reflect.full.findAnnotation

open class GenerateAliasFileTask: DefaultTask() {
    @TaskAction
    fun generate() {
        println("task被执行")
        val annotation = NewestApi::class
        annotation.findAnnotation<NewestApi>()
    }
}