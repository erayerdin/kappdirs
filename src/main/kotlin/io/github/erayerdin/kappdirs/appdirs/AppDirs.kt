package io.github.erayerdin.kappdirs.appdirs

import java.nio.file.Path

/**
 * An interface for implementing application directories.
 */
interface AppDirs {
    /**
     * @param appName The name of the application.
     * @param appVersion The version of the application.
     * @param appAuthor The author of the application. You can also use your organization's name.
     * It does not have any effect on Mac OS X, any Unix-based systems or any Linux distributions.
     * @param roaming Whether to point at roaming folder or not. It has effect only on Windows.
     * For more information, see [here](https://kappdirs.readthedocs.io/en/latest/windows-system/#what-is-roaming).
     * @return A directory containing data related to a specific user.
     */
    fun getUserDataDir(appName: String, appVersion: String, appAuthor: String? = null, roaming: Boolean = false): Path

    /**
     * @param appName The name of the application.
     * @param appVersion The version of the application.
     * @param appAuthor The author of the application. You can also use your organization's name.
     * It does not have any effect on Mac OS X, any Unix-based systems or any Linux distributions.
     * @param roaming Whether to point at roaming folder or not. It has effect only on Windows.
     * For more information, see [here](https://kappdirs.readthedocs.io/en/latest/windows-system/#what-is-roaming).
     * @return A directory containing configuration data of the application related to a specific user.
     */
    fun getUserConfigDir(appName: String, appVersion: String, appAuthor: String? = null, roaming: Boolean = false): Path

    /**
     * @param appName The name of the application.
     * @param appVersion The version of the application.
     * @param appAuthor The author of the application. You can also use your organization's name.
     * It does not have any effect on Mac OS X, any Unix-based systems or any Linux distributions.
     * @return A directory containing cache data of the application. It is a user-based directory.
     */
    fun getUserCacheDir(appName: String, appVersion: String, appAuthor: String? = null): Path

    /**
     * @param appName The name of the application.
     * @param appVersion The version of the application.
     * @param appAuthor The author of the application. You can also use your organization's name.
     * It does not have any effect on Mac OS X, any Unix-based systems or any Linux distributions.
     * @return A directory containing log files of the application. It is a user-based directory.
     */
    fun getUserLogDir(appName: String, appVersion: String, appAuthor: String? = null): Path

    /**
     * @return Downloads directory of the user.
     */
    fun getUserDownloadsDir(): Path

    /**
     * @return Desktop directory of the user.
     */
    fun getUserDesktopDir(): Path

    /**
     * @return Documents directory of the user.
     */
    fun getUserDocumentsDir(): Path

    /**
     * @param appName The name of the application.
     * @param appVersion The version of the application.
     * @param appAuthor The author of the application. You can also use your organization's name.
     * It does not have any effect on Mac OS X, any Unix-based systems or any Linux distributions.
     * @param local Whether to choose `/local` directory on Linux systems. It has effect only on
     * Linux distributions.
     * @return A directory containing system-wide data of the application.
     */
    fun getSiteDataDir(appName: String, appVersion: String, appAuthor: String? = null, local: Boolean = false): Path

    /**
     * @param appName The name of the application.
     * @param appVersion The version of the application.
     * @param appAuthor The author of the application. You can also use your organization's name.
     * It does not have any effect on Mac OS X, any Unix-based systems or any Linux distributions.
     * @return A directory containing system-wide configuration files of the application.
     */
    fun getSiteConfigDir(appName: String, appVersion: String, appAuthor: String? = null): Path
}