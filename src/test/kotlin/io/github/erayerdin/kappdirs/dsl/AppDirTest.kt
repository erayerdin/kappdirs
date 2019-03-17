package io.github.erayerdin.kappdirs.dsl

import io.github.erayerdin.kappdirs.appdirs.osName
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Test
import java.io.File

class AppDirTest {
    companion object {
        private val appDir = AppDir("foo", "0.1.0", "eray")
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

    @Test
    fun testUserConfig() {
        appDir {
            userConfig("bar", "baz.txt") { dir, file ->
                assertReadWriteOperations(dir, file)
            }
        }
    }

    @Test
    fun testUserConfigWithRoaming() {
        if (osName != null) {
            assumeTrue(osName.startsWith("windows"))
        }

        appDir {
            userConfig("bar", "baz.txt", roaming = true) { dir, file ->
                assertReadWriteOperations(dir, file)
            }
        }
    }

    @Test
    fun testUserCache() {
        appDir {
            userCache("bar", "baz.txt") { dir, file ->
                assertReadWriteOperations(dir, file)
            }
        }
    }
}
