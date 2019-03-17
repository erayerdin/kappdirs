package io.github.erayerdin.kappdirs.dsl

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

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

    @Test
    fun testUserData() {
        appDir {
            userData("bar", "baz.txt") { dir, file ->
                dir.mkdirs()
                file.createNewFile()

                file.writeText("lorem ipsum")
                assertTrue(file.exists())
                assertEquals("lorem ipsum", file.readText())

                file.delete()
            }
        }
    }
}
