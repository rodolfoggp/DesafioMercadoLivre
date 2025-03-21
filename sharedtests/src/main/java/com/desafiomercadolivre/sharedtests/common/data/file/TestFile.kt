package com.desafiomercadolivre.sharedtests.common.data.file

import java.io.FileNotFoundException
import java.net.URL

class TestFile(private val url: URL) {
    fun text() = url.readText()
    fun textReplacing(vararg values: Any) = String.format(text(), *values)
}

fun Any.file(path: String): TestFile {
    val filePath = this.javaClass.classLoader!!.getResource(path)
        ?: throw FileNotFoundException(path)
    return TestFile(filePath)
}
