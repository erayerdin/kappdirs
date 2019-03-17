package io.github.erayerdin.kappdirs.dsl

import io.github.erayerdin.kappdirs.AppDirsFactory
import io.github.erayerdin.kappdirs.appdirs.AppDirs
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

data class AppDir(
    val appName: String,
    val appVersion: String,
    val appAuthor: String? = null
) {
    private val appDirs: AppDirs = AppDirsFactory.getInstance()

    fun userData(vararg path: String, roaming: Boolean = false, method: (dir: File, file: File) -> Unit) {
        val realPath = appDirs.getUserDataDir(appName, appVersion, appAuthor, roaming).resolve(path)
        val file = realPath.toFile()
        val dir = File(file, "..")
        method(dir, file)
    }

    operator fun invoke(method: AppDir.() -> Unit) {
        method(this)
    }
}

private fun Path.resolve(path: Array<out String>): @ParameterName(name = "path") Path {
    val separator = System.getProperty("file.separator")
    val pathString = path.joinToString(separator)
    return Paths.get(this.toString(), pathString)
}
