package io.github.erayerdin.kappdirs.appdirs

import java.nio.file.Path
import java.nio.file.Paths

internal class UnixAppDirs: AppDirs {
    override fun getUserDataDir(appName: String, appVersion: String, appAuthor: String?, roaming: Boolean): Path {
        return Paths.get(
            HOME_DIR,
            arrayOf(".local", "share", appName, appVersion).joinToString(SEPARATOR)
        )
    }

    override fun getUserConfigDir(appName: String, appVersion: String, appAuthor: String?, roaming: Boolean): Path {
        return Paths.get(
            HOME_DIR,
            arrayOf(".config", appName, appVersion).joinToString(SEPARATOR)
        )
    }

    override fun getUserCacheDir(appName: String, appVersion: String, appAuthor: String?): Path {
        return Paths.get(
            HOME_DIR,
            arrayOf(".cache", appName, appVersion).joinToString(SEPARATOR)
        )
    }

    override fun getUserLogDir(appName: String, appVersion: String, appAuthor: String?): Path {
        return Paths.get(
            HOME_DIR,
            arrayOf(".cache", appName, "logs", appVersion).joinToString(SEPARATOR)
        )
    }

    override fun getSiteDataDir(appName: String, appVersion: String, appAuthor: String?, local: Boolean): Path {
        val directories: Array<String> = if (local) {
            arrayOf("usr", "local", "share", appName, appVersion)
        } else {
            arrayOf("usr", "share", appName, appVersion)
        }

        return Paths.get("/", directories.joinToString(SEPARATOR))
    }

    override fun getSiteConfigDir(appName: String, appVersion: String, appAuthor: String?): Path {
        return Paths.get("/", arrayOf("etc", appName, appVersion).joinToString(SEPARATOR))
    }
}