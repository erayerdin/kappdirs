package io.github.erayerdin.kappdirs.appdirs

import java.nio.file.Path
import java.nio.file.Paths

internal class OSXAppDirs: AppDirs {
    override fun getUserDataDir(appName: String, appVersion: String, appAuthor: String?, roaming: Boolean): Path {
        return Paths.get(
            HOME_DIR,
            arrayOf("Library", "Application Support", appName, appVersion).joinToString(SEPARATOR)
        )
    }

    override fun getUserConfigDir(appName: String, appVersion: String, appAuthor: String?, roaming: Boolean): Path {
        return getUserDataDir(appName, appVersion, appAuthor, roaming)
    }

    override fun getUserCacheDir(appName: String, appVersion: String, appAuthor: String?): Path {
        return Paths.get(
            HOME_DIR,
            arrayOf("Library", "Caches", appName, appVersion).joinToString(SEPARATOR)
        )
    }

    override fun getUserLogDir(appName: String, appVersion: String, appAuthor: String?): Path {
        return Paths.get(
            HOME_DIR,
            arrayOf("Library", "Logs", appName, appVersion).joinToString(SEPARATOR)
        )
    }

    override fun getUserDownloadsDir(): Path {
        // todo 1 - implement localized directories later on
        return Paths.get(HOME_DIR, "Downloads")
    }

    override fun getUserDesktopDir(): Path {
        // todo 1 - implement localized directories later on
        return Paths.get(HOME_DIR, "Desktop")
    }

    override fun getUserDocumentsDir(): Path {
        // todo 1 - implement localized directories later on
        return Paths.get(HOME_DIR, "Documents")
    }

    override fun getUserMusicDir(): Path {
        // todo 1 - implement localized directories later on
        return Paths.get(HOME_DIR, "Music")
    }

    override fun getUserPicturesDir(): Path {
        // todo 1 - implement localized directories later on
        return Paths.get(HOME_DIR, "Pictures")
    }

    override fun getUserVideosDir(): Path {
        // todo 1 - implement localized directories later on
        return Paths.get(HOME_DIR, "Videos")
    }

    override fun getSiteDataDir(appName: String, appVersion: String, appAuthor: String?, local: Boolean): Path {
        return Paths.get(
            "/",
            arrayOf("Library", "Application Support", appName, appVersion).joinToString(SEPARATOR)
        )
    }

    override fun getSiteConfigDir(appName: String, appVersion: String, appAuthor: String?): Path {
        return getSiteDataDir(appName, appVersion, appAuthor)
    }
}