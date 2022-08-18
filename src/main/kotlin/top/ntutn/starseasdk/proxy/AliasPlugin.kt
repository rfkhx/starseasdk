package top.ntutn.starseasdk.proxy

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.DependencyResolutionListener
import org.gradle.api.artifacts.DependencySet
import org.gradle.api.artifacts.ResolvableDependencies
import top.ntutn.starsea.starseasdk.BuildConfig

class AliasPlugin: Plugin<Project>, DependencyResolutionListener {
    private lateinit var project: Project
    private lateinit var compileOnlyDependencies: DependencySet
    private lateinit var task: GenerateAliasFileTask
    override fun apply(project: Project) {
        this.project = project
        project.tasks.create("generateStarseaApiAlias", GenerateAliasFileTask::class.java) {
            task = it
        }
        project.gradle.addListener(this)

        compileOnlyDependencies = project.configurations.getByName("compileOnly").dependencies
    }

    override fun beforeResolve(dependencies: ResolvableDependencies) {
        compileOnlyDependencies.add(project.dependencies.create("com.github.rfkhx:starseasdk:${BuildConfig.version}"))
        project.gradle.removeListener(this)
    }

    override fun afterResolve(dependencies: ResolvableDependencies) {
        task.generate()
    }
}