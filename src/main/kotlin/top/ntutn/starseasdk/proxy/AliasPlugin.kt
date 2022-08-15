package top.ntutn.starseasdk.proxy

import org.gradle.api.Plugin
import org.gradle.api.Project

class AliasPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.create("generateStarseaApiAlias", GenerateAliasFileTask::class.java
        ) {
            it.generate()
        }
    }
}