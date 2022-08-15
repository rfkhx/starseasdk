package top.ntutn.starseasdk.proxy

import org.gradle.api.Plugin
import org.gradle.api.Project

class AliasPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        println("看上去插件正常引入了")
    }
}