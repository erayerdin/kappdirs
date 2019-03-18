package io.github.erayerdin.kappdirs.appdirs

import org.junit.Assert.assertEquals
import org.junit.Test
import java.nio.file.Path
import java.nio.file.Paths

object ImplAppDirs: AppDirs {
    override fun getUserDataDir(appName: String, appVersion: String, appAuthor: String?, roaming: Boolean): Path = Paths.get("/a")
    override fun getUserConfigDir(appName: String, appVersion: String, appAuthor: String?, roaming: Boolean): Path = Paths.get("/b")
    override fun getUserCacheDir(appName: String, appVersion: String, appAuthor: String?): Path = Paths.get("/c")
    override fun getUserLogDir(appName: String, appVersion: String, appAuthor: String?): Path = Paths.get("/d")
    override fun getSiteDataDir(appName: String, appVersion: String, appAuthor: String?, local: Boolean): Path = Paths.get("/e")
    override fun getSiteConfigDir(appName: String, appVersion: String, appAuthor: String?): Path = Paths.get("/f")

    override fun getUserDownloadsDir(): Path  = Paths.get("/g")
    override fun getUserDesktopDir(): Path  = Paths.get("/h")
    override fun getUserDocumentsDir(): Path = Paths.get("/i")
    override fun getUserMusicDir(): Path = Paths.get("/j")
    override fun getUserPicturesDir(): Path = Paths.get("/k")
    override fun getUserVideosDir(): Path = Paths.get("/l")
}

class AppDirsTest {
    @Test
    fun testUserDataDir() = assertEquals(
        Paths.get("/a"),
        ImplAppDirs.getUserDataDir("foo", "0.1.0", "eray")
    )
    @Test
    fun testUserConfigDir() = assertEquals(
        Paths.get("/b"),
        ImplAppDirs.getUserConfigDir("foo", "0.1.0", "eray")
    )
    @Test
    fun testUserCacheDir() = assertEquals(
        Paths.get("/c"),
        ImplAppDirs.getUserCacheDir("foo", "0.1.0", "eray")
    )
    @Test
    fun testUserLogDir() = assertEquals(
        Paths.get("/d"),
        ImplAppDirs.getUserLogDir("foo", "0.1.0", "eray")
    )
    @Test
    fun testSiteDataDir() = assertEquals(
        Paths.get("/e"),
        ImplAppDirs.getSiteDataDir("foo", "0.1.0", "eray")
    )
    @Test
    fun testSiteConfigDir() = assertEquals(
        Paths.get("/f"),
        ImplAppDirs.getSiteConfigDir("foo", "0.1.0", "eray")
    )

    @Test
    fun testUserDownloadsDir() = assertEquals(
        Paths.get("/g"),
        ImplAppDirs.getUserDownloadsDir()
    )

    @Test
    fun testUserDesktopDir() = assertEquals(
        Paths.get("/h"),
        ImplAppDirs.getUserDesktopDir()
    )

    @Test
    fun testUserDocumentsDir() = assertEquals(
        Paths.get("/i"),
        ImplAppDirs.getUserDocumentsDir()
    )

    @Test
    fun testUserMusicDir() = assertEquals(
        Paths.get("/j"),
        ImplAppDirs.getUserMusicDir()
    )

    @Test
    fun testUserPicturesDir() = assertEquals(
        Paths.get("/k"),
        ImplAppDirs.getUserPicturesDir()
    )

    @Test
    fun testUserVideosDir() = assertEquals(
        Paths.get("/l"),
        ImplAppDirs.getUserVideosDir()
    )
}