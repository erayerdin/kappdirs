package io.github.erayerdin.kappdirs.dsl

import io.github.erayerdin.kappdirs.appdirs.originalHome
import io.github.erayerdin.kappdirs.appdirs.osName
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.BeforeClass
import org.junit.Test
import java.io.File

class AppDirTest {
    companion object {
        private val appDir = AppDir("foo", "0.1.0", "eray")

        @JvmStatic
        @BeforeClass
        fun setUpClass() {
            when {
                osName!!.startsWith("mac") -> System.setProperty("user.home", "/Users/bar")
                osName.startsWith("windows") -> {}
                else -> System.setProperty("user.home", "/home/bar")
            }
            System.setProperty("user.home", "/home/bar")
        }

        @JvmStatic
        @AfterClass
        fun tearDownClass() {
            System.setProperty("user.home", originalHome)
        }
    }

    @Test
    fun testPropertiesContext() {
        appDir {
            assertEquals("foo", appName)
            assertEquals("0.1.0", appVersion)
            assertEquals("eray", appAuthor)
        }
    }

    private fun assertReadWriteOperations(dir: File, file: File, create: Boolean = true) {
        if (create) {
            dir.mkdirs()
            file.createNewFile()
        }

        file.writeText("lorem ipsum")
        assertTrue(file.exists())
        assertEquals("lorem ipsum", file.readText())

        file.delete()
    }

    @Test
    fun testUserData() {
        appDir {
            userData("bar", "baz.txt") { dir, file ->
                assertReadWriteOperations(dir, file)
            }
        }
    }

    @Test
    fun testUserDataWithRoaming() {
        if (osName != null) {
            assumeTrue(osName.startsWith("windows"))
        }

        appDir {
            userData("bar", "baz.txt", roaming = true) { dir, file ->
                assertReadWriteOperations(dir, file)
            }
        }
    }
}
