package io.github.erayerdin.kappdirs

import io.github.erayerdin.kappdirs.appdirs.OSXAppDirs
import io.github.erayerdin.kappdirs.appdirs.UnixAppDirs
import io.github.erayerdin.kappdirs.appdirs.WindowsAppDirs

private val osName = System.getProperty("os.name").toLowerCase()

class AppDirsFactory {
    companion object {
        private var appDirs: AppDirs? = null

        fun getInstance(): AppDirs {
            if (appDirs != null) {
                return appDirs as AppDirs
            }

            when {
                osName.startsWith("windows") -> appDirs = WindowsAppDirs()
                osName.startsWith("mac") -> appDirs = OSXAppDirs()
                else -> appDirs = UnixAppDirs()
            }

            return appDirs as AppDirs
        }
    }
}

