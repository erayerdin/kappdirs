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

class UnixAppDirsTest {
    companion object {
        val appDirs: AppDirs = UnixAppDirs()

//        @JvmStatic
//        @BeforeClass
//        fun setUpClass() {
//            System.setProperty("user.home", "$home")
//        }
//
//        @JvmStatic
//        @AfterClass
//        fun tearDownClass() {
//            System.setProperty("user.home", home)
//        }
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