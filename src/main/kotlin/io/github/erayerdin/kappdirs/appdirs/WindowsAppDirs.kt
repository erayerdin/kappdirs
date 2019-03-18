package io.github.erayerdin.kappdirs.appdirs

import java.nio.file.Path
import java.nio.file.Paths

internal class WindowsAppDirs: AppDirs {
    override fun getUserDataDir(appName: String, appVersion: String, appAuthor: String?, roaming: Boolean): Path {
        val appData: String = if (roaming)
            APPDATA
        else
            LOCALAPPDATA

        return when (appAuthor) {
            null -> Paths.get(appData, appName, appVersion)
            else -> Paths.get(appData, appAuthor, appName, appVersion)
        }
    }

    override fun getUserConfigDir(appName: String, appVersion: String, appAuthor: String?, roaming: Boolean): Path {
        return getUserDataDir(appName, appVersion, appAuthor, roaming)
    }

    override fun getUserCacheDir(appName: String, appVersion: String, appAuthor: String?): Path {
        return when (appAuthor) {
            null -> Paths.get(LOCALAPPDATA, appName, "Cache", appVersion)
            else -> Paths.get(LOCALAPPDATA, appAuthor, appName, "Cache", appVersion)
        }
    }

    override fun getUserLogDir(appName: String, appVersion: String, appAuthor: String?): Path {
        return when (appAuthor) {
            null -> Paths.get(LOCALAPPDATA, appName, "Logs", appVersion)
            else -> Paths.get(LOCALAPPDATA, appAuthor, appName, "Logs", appVersion)
        }
    }

    override fun getUserDownloadsDir(): Path {
        return Paths.get(HOME_DIR, "Downloads")
    }

    override fun getSiteDataDir(appName: String, appVersion: String, appAuthor: String?, local: Boolean): Path {
        return when (appAuthor) {
            null -> Paths.get(PROGRAMDATA, appName, appVersion)
            else -> Paths.get(PROGRAMDATA, appAuthor, appName, appVersion)
        }
    }

    override fun getSiteConfigDir(appName: String, appVersion: String, appAuthor: String?): Path {
        return getSiteDataDir(appName, appVersion, appAuthor)
    }
}