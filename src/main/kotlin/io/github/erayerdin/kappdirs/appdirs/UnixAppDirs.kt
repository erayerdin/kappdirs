package io.github.erayerdin.kappdirs.appdirs

import io.github.erayerdin.kappdirs.AppDirs
import java.nio.file.Paths

private val HOME_DIR = System.getProperty("user.home")
private val SEPARATOR = System.getProperty("file.separator")

internal class UnixAppDirs: AppDirs {
    override fun getUserDataDir(appName: String, appVersion: String, appAuthor: String, roaming: Boolean): String {
        val path = Paths.get(
            HOME_DIR,
            arrayOf(".local", "share", appName, appVersion).joinToString(SEPARATOR)
        )

        return path.toString()
    }

    override fun getUserConfigDir(appName: String, appVersion: String, appAuthor: String, roaming: Boolean): String {
        val path = Paths.get(
            HOME_DIR,
            arrayOf(".config", appName, appVersion).joinToString(SEPARATOR)
        )

        return path.toString()
    }

    override fun getUserCacheDir(appName: String, appVersion: String, appAuthor: String): String {
        val path = Paths.get(
            HOME_DIR,
            arrayOf(".cache", appName, appVersion).joinToString(SEPARATOR)
        )

        return path.toString()
    }

    override fun getUserLogDir(appName: String, appVersion: String, appAuthor: String): String {
        val path = Paths.get(
            HOME_DIR,
            arrayOf(".cache", appName, "logs", appVersion).joinToString(SEPARATOR)
        )

        return path.toString()
    }

    override fun getSiteDataDir(appName: String, appVersion: String, appAuthor: String, local: Boolean): String {
        val directories: Array<String> = if (local) {
            arrayOf("usr", "local", "share", appName, appVersion)
        } else {
            arrayOf("usr", "share", appName, appVersion)
        }

        val path = Paths.get("/", directories.joinToString(SEPARATOR))
        return path.toString()
    }

    override fun getSiteConfigDir(appName: String, appVersion: String, appAuthor: String, local: Boolean): String {
        val path = Paths.get("/", arrayOf("etc", appName, appVersion).joinToString(SEPARATOR))
        return path.toString()
    }
}