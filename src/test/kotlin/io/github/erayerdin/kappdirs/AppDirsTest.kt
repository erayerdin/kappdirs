package io.github.erayerdin.kappdirs

import io.github.erayerdin.kappdirs.appdirs.AppDirs
import org.junit.Assert.*
import org.junit.Test

object ImplAppDirs: AppDirs {
    override fun getUserDataDir(appName: String, appVersion: String, appAuthor: String, roaming: Boolean): String = "a"
    override fun getUserConfigDir(appName: String, appVersion: String, appAuthor: String, roaming: Boolean): String = "b"
    override fun getUserCacheDir(appName: String, appVersion: String, appAuthor: String): String = "c"
    override fun getUserLogDir(appName: String, appVersion: String, appAuthor: String): String = "d"
    override fun getSiteDataDir(appName: String, appVersion: String, appAuthor: String, local: Boolean): String = "e"
    override fun getSiteConfigDir(appName: String, appVersion: String, appAuthor: String, local: Boolean): String = "f"

}

class AppDirsTest {
    @Test
    fun testUserDataDir() = assertEquals(
        "a",
        ImplAppDirs.getUserDataDir("foo", "0.1.0", "eray")
    )
    @Test
    fun testUserConfigDir() = assertEquals(
        "b",
        ImplAppDirs.getUserConfigDir("foo", "0.1.0", "eray")
    )
    @Test
    fun testUserCacheDir() = assertEquals(
        "c",
        ImplAppDirs.getUserCacheDir("foo", "0.1.0", "eray")
    )
    @Test
    fun testUserLogDir() = assertEquals(
        "d",
        ImplAppDirs.getUserLogDir("foo", "0.1.0", "eray")
    )
    @Test
    fun testSiteDataDir() = assertEquals(
        "e",
        ImplAppDirs.getSiteDataDir("foo", "0.1.0", "eray")
    )
    @Test
    fun testSiteConfigDir() = assertEquals(
        "f",
        ImplAppDirs.getSiteConfigDir("foo", "0.1.0", "eray")
    )
}