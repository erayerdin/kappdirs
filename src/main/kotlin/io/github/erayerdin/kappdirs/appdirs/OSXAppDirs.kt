package io.github.erayerdin.kappdirs.appdirs

import io.github.erayerdin.kappdirs.AppDirs
import java.nio.file.Paths

internal class OSXAppDirs: AppDirs {
    override fun getUserDataDir(appName: String, appVersion: String, appAuthor: String, roaming: Boolean): String {
        val path = Paths.get(
            HOME_DIR,
            arrayOf("Library", "Application Support", appName, appVersion).joinToString(SEPARATOR)
        )

        return path.toString()
    }

    override fun getUserConfigDir(appName: String, appVersion: String, appAuthor: String, roaming: Boolean): String {
        return getUserDataDir(appName, appVersion, appAuthor, roaming)
    }

    override fun getUserCacheDir(appName: String, appVersion: String, appAuthor: String): String {
        val path = Paths.get(
            HOME_DIR,
            arrayOf("Library", "Caches", appName, appVersion).joinToString(SEPARATOR)
        )

        return path.toString()
    }

    override fun getUserLogDir(appName: String, appVersion: String, appAuthor: String): String {
        val path = Paths.get(
            HOME_DIR,
            arrayOf("Library", "Logs", appName, appVersion).joinToString(SEPARATOR)
        )

        return path.toString()
    }

    override fun getSiteDataDir(appName: String, appVersion: String, appAuthor: String, local: Boolean): String {
        val path = Paths.get(
            "/",
            arrayOf("Library", "Application Support", appName, appVersion).joinToString(SEPARATOR)
        )

        return path.toString()
    }

    override fun getSiteConfigDir(appName: String, appVersion: String, appAuthor: String, local: Boolean): String {
        return getSiteDataDir(appName, appVersion, appAuthor, local)
    }
}