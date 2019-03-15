package io.github.erayerdin.kappdirs

import io.github.erayerdin.kappdirs.appdirs.UnixAppDirs

class AppDirsFactory {
    companion object {
        var appDirs: AppDirs? = null

        fun getInstance(): AppDirs {
            if (appDirs != null) {
                return appDirs as AppDirs
            }

            appDirs = UnixAppDirs()
            return appDirs as AppDirs
        }
    }
}

