package io.github.erayerdin.kappdirs.appdirs

import org.junit.*
import org.junit.Assert.assertEquals

class OSXAppDirsTest {
    companion object {
        val appDirs: AppDirs = OSXAppDirs()

        @JvmStatic
        @BeforeClass
        fun setUpClass() {
            System.setProperty("user.home", "/Users/bar")
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
            Assume.assumeTrue(osName.startsWith("mac"))
        }
    }

    @Test
    fun testUserDataDir() {
        assertEquals(
            "/Users/bar/Library/Application Support/foo/0.1.0",
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
            "/Users/bar/Library/Caches/foo/0.1.0",
            appDirs.getUserCacheDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testUserLogDir() {
        assertEquals(
            "/Users/bar/Library/Logs/foo/0.1.0",
            appDirs.getUserLogDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testSiteDataDir() {
        assertEquals(
            "/Library/Application Support/foo/0.1.0",
            appDirs.getSiteDataDir(appName, appVersion, appAuthor)
        )
    }

    @Test
    fun testSiteConfigDir() {
        testSiteDataDir()
    }
}