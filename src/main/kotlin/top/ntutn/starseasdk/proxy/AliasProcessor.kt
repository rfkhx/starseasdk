package top.ntutn.starseasdk.proxy

import com.google.auto.service.AutoService
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.lang.model.element.TypeElement
import javax.tools.FileObject
import javax.tools.StandardLocation

@AutoService(Processor::class)
@SupportedAnnotationTypes("top.ntutn.starseasdk.proxy.NewestApi")
class AliasProcessor : AbstractProcessor() {

    override fun process(set: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        if (set.isNullOrEmpty() || roundEnvironment == null) {
            return false
        }
        val outputPackageName = "top.ntutn.starseasdk.proxy"
        var outputContent = """
            package $outputPackageName
            
            /**
             * generated by AliasProcessor
             * 这个类是对tg机器人框架starsea提供的最新版本API的alias
             * 用于固定包名，避免升级版本号后有一大片包名要改
             * have a nice day.
             */
             
             object NewestApiInformation {
                val newestApiClassNames = 
        """.trimIndent()
        outputContent += roundEnvironment.getElementsAnnotatedWith(NewestApi::class.java).mapNotNull {
            it as TypeElement
            it.qualifiedName
        }.joinToString(", ", "listOf(", ")")
        outputContent += "\n}"
        val filerSourceFile: FileObject = processingEnv.filer.createResource(
            StandardLocation.SOURCE_OUTPUT,
            outputPackageName, "CompatAlias.kt"
        )
        with(filerSourceFile.openOutputStream()) {
            write(outputContent.toByteArray())
            flush()
            close()
        }
        return true
    }
}