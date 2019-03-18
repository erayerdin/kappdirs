package io.github.erayerdin.kappdirs.appdirs

import org.junit.Assert.assertEquals
import org.junit.Assume
import org.junit.Before
import org.junit.Test
import java.nio.file.Paths

internal val home = System.getProperty("user.home")
internal val osName = System.getProperty("os.name")?.toLowerCase()

internal const val appName = "foo"
internal const val appVersion = "0.1.0"

internal const val LOCALIZATION_FAILURE = "This test probably failed because the target test machine probably is not set to en_US locale."

class UnixAppDirsTest {
    companion object {
        val appDirs: AppDirs = UnixAppDirs()
    }

    @Before
    fun setUp() {
        if (osName != null) {
            Assume.assumeTrue(!(osName.startsWith("windows") || osName.startsWith("mac")))
        }
    }

    @Test
    fun testUserDataDir() {
        assertEquals(
            Paths.get("$home/.local/share/foo/0.1.0"),
            appDirs.getUserDataDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testUserConfigDir() {
        assertEquals(
            Paths.get("$home/.config/foo/0.1.0"),
            appDirs.getUserConfigDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testUserCacheDir() {
        assertEquals(
            Paths.get("$home/.cache/foo/0.1.0"),
            appDirs.getUserCacheDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testUserLogDir() {
        assertEquals(
            Paths.get("$home/.cache/foo/logs/0.1.0"),
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
            appDirs.getUserDesktopDir()
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
    fun testLocalSiteDataDir() {
        assertEquals(
            Paths.get("/usr/local/share/foo/0.1.0"),
            appDirs.getSiteDataDir(appName, appVersion, appAuthor, true)
        )
    }

    @Test
    fun testSiteDataDir() {
        assertEquals(
            Paths.get("/usr/share/foo/0.1.0"),
            appDirs.getSiteDataDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testSiteConfigDir() {
        assertEquals(
            Paths.get("/etc/foo/0.1.0"),
            appDirs.getSiteConfigDir(appName, appVersion, appAuthor)
        )
    }
}