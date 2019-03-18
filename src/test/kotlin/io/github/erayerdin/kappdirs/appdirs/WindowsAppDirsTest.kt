package io.github.erayerdin.kappdirs.appdirs

import org.junit.Assert.assertTrue
import org.junit.Assume
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

private val systemDrive = System.getenv("SystemDrive")
internal const val appAuthor = "eray"

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
        assertTrue(dir.toString().startsWith("$systemDrive\\Users\\"))
        assertTrue(dir.toString().endsWith("\\AppData\\Local\\eray\\foo\\0.1.0"))
    }

    @Test
    fun testLocalUserDataDirWithoutAppAuthor() {
        val dir = appDirs.getUserDataDir(appName, appVersion, null)
        assertTrue(dir.toString().startsWith("$systemDrive\\Users\\"))
        assertTrue(dir.toString().endsWith("\\AppData\\Local\\foo\\0.1.0"))
    }

    @Test
    fun testRoamingUserDataDir() {
        val dir = appDirs.getUserDataDir(appName, appVersion, appAuthor, true)
        assertTrue(dir.toString().startsWith("$systemDrive\\Users\\"))
        assertTrue(dir.toString().endsWith("\\AppData\\Roaming\\eray\\foo\\0.1.0"))
    }

    @Test
    fun testRoamingUserDataDirWithoutAppAuthor() {
        val dir = appDirs.getUserDataDir(appName, appVersion, null, true)
        assertTrue(dir.toString().startsWith("$systemDrive\\Users\\"))
        assertTrue(dir.toString().endsWith("\\AppData\\Roaming\\foo\\0.1.0"))
    }

    @Test
    fun testLocalUserConfigDir() {
        testLocalUserDataDir()
    }

    @Test
    fun testLocalUserConfigDirWithoutAppAuthor() {
        testLocalUserDataDirWithoutAppAuthor()
    }

    @Test
    fun testRoamingUserConfigDirWithoutAppAuthor() {
        testRoamingUserDataDirWithoutAppAuthor()
    }

    @Test
    fun testUserCacheDir() {
        val dir = appDirs.getUserCacheDir(appName, appVersion, appAuthor)
        assertTrue(dir.toString().startsWith("$systemDrive\\Users"))
        assertTrue(dir.toString().endsWith("\\AppData\\Local\\eray\\foo\\Cache\\0.1.0"))
    }

    @Test
    fun testUserCacheDirWithoutAuthor() {
        val dir = appDirs.getUserCacheDir(appName, appVersion, null)
        assertTrue(dir.toString().startsWith("$systemDrive\\Users"))
        assertTrue(dir.toString().endsWith("\\AppData\\Local\\foo\\Cache\\0.1.0"))
    }

    @Test
    fun testUserLogDir() {
        val dir = appDirs.getUserLogDir(appName, appVersion, appAuthor)
        assertTrue(dir.toString().startsWith("$systemDrive\\Users"))
        assertTrue(dir.toString().endsWith("\\AppData\\Local\\eray\\foo\\Logs\\0.1.0"))
    }

    @Test
    fun testUserLogDirWithoutAppAuthor() {
        val dir = appDirs.getUserLogDir(appName, appVersion, null)
        assertTrue(dir.toString().startsWith("$systemDrive\\Users"))
        assertTrue(dir.toString().endsWith("\\AppData\\Local\\foo\\Logs\\0.1.0"))
    }

    @Test
    fun testSiteDataDir() {
        val dir = appDirs.getSiteDataDir(appName, appVersion, appAuthor)
        assertEquals("$systemDrive\\ProgramData\\eray\\foo\\0.1.0", dir.toString())
    }

    @Test
    fun testSiteDataDirWithoutAppAuthor() {
        val dir = appDirs.getSiteDataDir(appName, appVersion, null)
        assertEquals("$systemDrive\\ProgramData\\foo\\0.1.0", dir.toString())
    }

    @Test
    fun testUserDownloadsDir() {
        val dir = appDirs.getUserDownloadsDir()
        assertTrue(dir.toString().startsWith("$systemDrive\\Users"))
        assertTrue(dir.toString().endsWith("\\Downloads"))
    }

    @Test
    fun testUserDesktopDir() {
        val dir = appDirs.getUserDownloadsDir()
        assertTrue(dir.toString().startsWith("$systemDrive\\Users"))
        assertTrue(dir.toString().endsWith("\\Desktop"))
    }

    @Test
    fun testUserDocumentsDir() {
        val dir = appDirs.getUserDownloadsDir()
        assertTrue(dir.toString().startsWith("$systemDrive\\Users"))
        assertTrue(dir.toString().endsWith("\\My Documents"))
    }

    @Test
    fun testSiteConfigDir() {
        testSiteDataDir()
    }

    @Test
    fun testSiteConfigDirWithoutAppAuthor() {
        testSiteDataDirWithoutAppAuthor()
    }
}