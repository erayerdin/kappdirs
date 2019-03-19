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

    private fun invokeLambda(rootPath: Path, realPath: Path, method: (root: File, parent: File, file: File) -> Unit) {
        val file = realPath.toFile()
        method(rootPath.toFile(), file.parentFile, file)
    }

    fun userData(vararg path: String, roaming: Boolean = false, method: (root: File, parent: File, file: File) -> Unit) {
        val rootPath = appDirs.getUserDataDir(appName, appVersion, appAuthor, roaming)
        val realPath = rootPath.resolve(path)
        invokeLambda(rootPath, realPath, method)
    }

    fun userConfig(vararg path: String, roaming: Boolean = false, method: (root: File, parent: File, file: File) -> Unit) {
        val rootPath = appDirs.getUserConfigDir(appName, appVersion, appAuthor, roaming)
        val realPath = rootPath.resolve(path)
        invokeLambda(rootPath, realPath, method)
    }

    fun userCache(vararg path: String, method: (root: File, parent: File, file: File) -> Unit) {
        val rootPath = appDirs.getUserCacheDir(appName, appVersion, appAuthor)
        val realPath = rootPath.resolve(path)
        invokeLambda(rootPath, realPath, method)
    }

    fun userLog(vararg path: String, method: (root: File, parent: File, file: File) -> Unit) {
        val rootPath = appDirs.getUserLogDir(appName, appVersion, appAuthor)
        val realPath = rootPath.resolve(path)
        invokeLambda(rootPath, realPath, method)
    }

    fun userDownloads(vararg path: String, method: (root: File, parent: File, file: File) -> Unit) {
        val rootPath = appDirs.getUserDownloadsDir()
        val realPath = rootPath.resolve(path)
        invokeLambda(rootPath, realPath, method)
    }

    fun userDesktop(vararg path: String, method: (root: File, parent: File, file: File) -> Unit) {
        val rootPath = appDirs.getUserDesktopDir()
        val realPath = rootPath.resolve(path)
        invokeLambda(rootPath, realPath, method)
    }

    fun siteData(vararg path: String, local: Boolean = false, method: (root: File, parent: File, file: File) -> Unit) {
        val rootPath = appDirs.getSiteDataDir(appName, appVersion, appAuthor, local)
        val realPath = rootPath.resolve(path)
        invokeLambda(rootPath, realPath, method)
    }

    fun siteConfig(vararg path: String, method: (root: File, parent: File, file: File) -> Unit) {
        val rootPath = appDirs.getSiteConfigDir(appName, appVersion, appAuthor)
        val realPath = rootPath.resolve(path)
        invokeLambda(rootPath, realPath, method)
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
