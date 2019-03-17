package io.github.erayerdin.kappdirs.appdirs

import org.junit.Assert.assertTrue
import org.junit.Assume
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

private val systemDrive = System.getenv("SystemDrive")

class WindowsAppDirsTest {
    companion object {
        val appDirs: AppDirs = WindowsAppDirs()
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
        val dir = appDirs.getUserDataDir(appName, appVersion, appAuthor, true)
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
        assertEquals("$systemDrive\\ProgramData\\eray\\foo\\0.1.0", dir.toString())
    }

    @Test
    fun testSiteConfigDir() {
        testSiteDataDir()
    }
}