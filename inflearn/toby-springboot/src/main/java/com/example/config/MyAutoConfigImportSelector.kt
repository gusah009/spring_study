package com.example.config

import org.springframework.boot.context.annotation.ImportCandidates
import org.springframework.context.annotation.DeferredImportSelector
import org.springframework.core.type.AnnotationMetadata

class MyAutoConfigImportSelector(
    private val classLoader: ClassLoader
) : DeferredImportSelector {

    override fun selectImports(importingClassMetadata: AnnotationMetadata): Array<String> {
        val autoConfigs = mutableListOf<String>()

        ImportCandidates.load(MyAutoConfiguration::class.java, classLoader)
            .forEach { autoConfigs.add(it) }

        return autoConfigs.toTypedArray()
    }
}
