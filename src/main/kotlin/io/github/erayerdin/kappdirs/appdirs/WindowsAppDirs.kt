package io.github.erayerdin.kappdirs.appdirs

import io.github.erayerdin.kappdirs.AppDirs
import java.nio.file.Path
import java.nio.file.Paths

internal class WindowsAppDirs: AppDirs {
    override fun getUserDataDir(appName: String, appVersion: String, appAuthor: String, roaming: Boolean): String {
        val appData: String
        val path: Path

        if (roaming)
            appData = APPDATA
        else
            appData = LOCALAPPDATA

        path = Paths.get(appData, appAuthor, appName, appVersion)
        return path.toString()
    }

    override fun getUserConfigDir(appName: String, appVersion: String, appAuthor: String, roaming: Boolean): String {
        return getUserDataDir(appName, appVersion, appAuthor, roaming)
    }

    override fun getUserCacheDir(appName: String, appVersion: String, appAuthor: String): String {
        val path = Paths.get(LOCALAPPDATA, appAuthor, appName, "Cache", appVersion)
        return path.toString()
    }

    override fun getUserLogDir(appName: String, appVersion: String, appAuthor: String): String {
        val path = Paths.get(LOCALAPPDATA, appAuthor, appName, "Logs", appVersion)
        return path.toString()
    }

    override fun getSiteDataDir(appName: String, appVersion: String, appAuthor: String, local: Boolean): String {
        val path = Paths.get(PROGRAMDATA, appAuthor, appName, appVersion)
        return path.toString()
    }

    override fun getSiteConfigDir(appName: String, appVersion: String, appAuthor: String, local: Boolean): String {
        return getSiteDataDir(appName, appVersion, appAuthor, local)
    }
}