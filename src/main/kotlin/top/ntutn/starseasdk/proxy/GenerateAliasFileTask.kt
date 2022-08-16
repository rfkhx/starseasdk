package top.ntutn.starseasdk.proxy

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class GenerateAliasFileTask: DefaultTask() {
    @TaskAction
    fun generate() {
        println("task被执行")
    }
}