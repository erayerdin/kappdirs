package io.github.erayerdin.kappdirs.appdirs

import io.github.erayerdin.kappdirs.AppDirs
import org.junit.Assert.assertTrue
import org.junit.Assume
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import kotlin.test.assertEquals

private val systemDrive = System.getenv("SystemDrive")

class WindowsAppDirsTest {
    companion object {
        val appDirs: AppDirs = WindowsAppDirs()

        @JvmStatic
        @BeforeClass
        fun setUpClass() {
            println("Local User Data Dir: ${appDirs.getUserDataDir(appName, appVersion, appAuthor)}")
            println("Roaming User Data Dir: ${appDirs.getUserDataDir(appName, appVersion, appAuthor, true)}")

            println("Local User Config Dir: ${appDirs.getUserConfigDir(appName, appVersion, appAuthor)}")
            println("Roaming User Config Dir: ${appDirs.getUserConfigDir(appName, appVersion, appAuthor, true)}")

            println("Cache Dir: ${appDirs.getUserCacheDir(appName, appVersion, appAuthor)}")
            println("Log Dir: ${appDirs.getUserLogDir(appName, appVersion, appAuthor)}")

            println("Local Site Data Dir: ${appDirs.getSiteDataDir(appName, appVersion, appAuthor, true)}")
            println("Roaming Site Data Dir: ${appDirs.getSiteDataDir(appName, appVersion, appAuthor)}")

            println("Local Site Config Dir: ${appDirs.getSiteConfigDir(appName, appVersion, appAuthor, true)}")
            println("Roaming Site Config Dir: ${appDirs.getSiteConfigDir(appName, appVersion, appAuthor)}")
            println(systemDrive)
        }
    }

    @Before
    fun setUp() {
        if (osName != null) {
            Assume.assumeTrue(osName.startsWith("windows"))
        }
    }

    @Test
    fun testLocalUserDataDir() {
        val dir = appDirs.getUserDataDir(appName, appVersion, appAuthor)
        assertTrue(dir.startsWith("$systemDrive\\Users\\"))
        assertTrue(dir.endsWith("\\AppData\\Local\\eray\\foo\\0.1.0"))
    }

    @Test
    fun testRoamingUserDataDir() {
        val dir = appDirs.getUserDataDir(appName, appVersion, appAuthor)
        assertTrue(dir.startsWith("$systemDrive\\Users\\"))
        assertTrue(dir.endsWith("\\AppData\\Roaming\\eray\\foo\\0.1.0"))
    }

    @Test
    fun testLocalUserConfigDir() {
        testLocalUserDataDir()
    }

    @Test
    fun testRoamingUserConfigDir() {
        testRoamingUserDataDir()
    }

    @Test
    fun testUserCacheDir() {
        val dir = appDirs.getUserCacheDir(appName, appVersion, appAuthor)
        assertTrue(dir.startsWith("$systemDrive\\Users"))
        assertTrue(dir.endsWith("\\AppData\\Local\\eray\\foo\\Cache\\0.1.0"))
    }

    @Test
    fun testUserLogDir() {
        val dir = appDirs.getUserLogDir(appName, appVersion, appAuthor)
        assertTrue(dir.startsWith("$systemDrive\\Users"))
        assertTrue(dir.endsWith("\\AppData\\Local\\eray\\foo\\Logs\\0.1.0"))
    }

    @Test
    fun testSiteDataDir() {
        val dir = appDirs.getSiteDataDir(appName, appVersion, appAuthor)
        assertEquals("$systemDrive\\ProgramData\\eray\\foo\\0.1.0", dir)
    }

    @Test
    fun testSiteConfigDir() {
        testSiteDataDir()
    }
}