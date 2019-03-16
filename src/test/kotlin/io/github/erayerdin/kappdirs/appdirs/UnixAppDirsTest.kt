package io.github.erayerdin.kappdirs.appdirs

import io.github.erayerdin.kappdirs.AppDirs
import org.junit.*
import org.junit.Assert.assertEquals

internal val originalHome = System.getProperty("user.home")
internal val osName = System.getProperty("os.name")?.toLowerCase()

internal const val appAuthor = "eray"
internal const val appName = "foo"
internal const val appVersion = "0.1.0"

class UnixAppDirsTest {
    companion object {
        val appDirs: AppDirs = UnixAppDirs()

        @JvmStatic
        @BeforeClass
        fun setUpClass() {
            System.setProperty("user.home", "/home/bar")
        }

        @JvmStatic
        @AfterClass
        fun tearDownClass() {
            System.setProperty("user.home", originalHome)
        }
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
            "/home/bar/.local/share/foo/0.1.0",
            appDirs.getUserDataDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testUserConfigDir() {
        assertEquals(
            "/home/bar/.config/foo/0.1.0",
            appDirs.getUserConfigDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testUserCacheDir() {
        assertEquals(
            "/home/bar/.cache/foo/0.1.0",
            appDirs.getUserCacheDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testUserLogDir() {
        assertEquals(
            "/home/bar/.cache/foo/logs/0.1.0",
            appDirs.getUserLogDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testLocalSiteDataDir() {
        assertEquals(
            "/usr/local/share/foo/0.1.0",
            appDirs.getSiteDataDir(appName, appVersion, appAuthor, true)
        )
    }

    @Test
    fun testSiteDataDir() {
        assertEquals(
            "/usr/share/foo/0.1.0",
            appDirs.getSiteDataDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testSiteConfigDir() {
        assertEquals(
            "/etc/foo/0.1.0",
            appDirs.getSiteConfigDir(appName, appVersion, appAuthor)
        )
    }
}