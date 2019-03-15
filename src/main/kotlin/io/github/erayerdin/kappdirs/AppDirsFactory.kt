package io.github.erayerdin.kappdirs

import io.github.erayerdin.kappdirs.appdirs.OSXAppDirs
import io.github.erayerdin.kappdirs.appdirs.UnixAppDirs

private val osName = System.getProperty("os.name").toLowerCase()

class AppDirsFactory {
    companion object {
        var appDirs: AppDirs? = null

        fun getInstance(): AppDirs {
            if (appDirs != null) {
                return appDirs as AppDirs
            }

            if (osName.startsWith("mac"))
                appDirs = OSXAppDirs()
            else
                appDirs = UnixAppDirs()

            return appDirs as AppDirs
        }
    }
}

