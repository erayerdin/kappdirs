package io.github.erayerdin.kappdirs.appdirs

import org.junit.Assert.assertEquals
import org.junit.Assume
import org.junit.Before
import org.junit.Test
import java.nio.file.Paths

class OSXAppDirsTest {
    companion object {
        val appDirs: AppDirs = OSXAppDirs()
    }

    @Before
    fun setUp() {
        if (osName != null) {
            Assume.assumeTrue(osName.startsWith("mac"))
        }
    }

    @Test
    fun testUserDataDir() {
        assertEquals(
            Paths.get("$home/Library/Application Support/foo/0.1.0"),
            appDirs.getUserDataDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testUserConfigDir() {
        testUserDataDir()
    }

    @Test
    fun testUserCacheDir() {
        assertEquals(
            Paths.get("$home/Library/Caches/foo/0.1.0"),
            appDirs.getUserCacheDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testUserLogDir() {
        assertEquals(
            Paths.get("$home/Library/Logs/foo/0.1.0"),
            appDirs.getUserLogDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testUserDownloadsDir() {
        assertEquals(
            LOCALIZATION_FAILURE,
            Paths.get("$home/Downloads"),
            appDirs.getUserDownloadsDir()
        )
    }

    @Test
    fun testUserDesktopDir() {
        assertEquals(
            LOCALIZATION_FAILURE,
            Paths.get("$home/Desktop"),
            appDirs.getUserDesktopDir()
        )
    }

    @Test
    fun testUserDocumentsDir() {
        assertEquals(
            LOCALIZATION_FAILURE,
            Paths.get("$home/Documents"),
            appDirs.getUserDocumentsDir()
        )
    }

    @Test
    fun testUserMusicDir() {
        assertEquals(
            LOCALIZATION_FAILURE,
            Paths.get("$home/Music"),
            appDirs.getUserMusicDir()
        )
    }

    @Test
    fun testUserPicturesDir() {
        assertEquals(
            LOCALIZATION_FAILURE,
            Paths.get("$home/Pictures"),
            appDirs.getUserPicturesDir()
        )
    }

    @Test
    fun testUserVideosDir() {
        assertEquals(
            LOCALIZATION_FAILURE,
            Paths.get("$home/Videos"),
            appDirs.getUserVideosDir()
        )
    }

    @Test
    fun testSiteDataDir() {
        assertEquals(
            Paths.get("/Library/Application Support/foo/0.1.0"),
            appDirs.getSiteDataDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testSiteConfigDir() {
        testSiteDataDir()
    }
}