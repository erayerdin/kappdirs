package io.github.erayerdin.kappdirs

import org.junit.Test
import kotlin.test.assertSame

private val osName = System.getProperty("os.name").toLowerCase()

class AppDirsFactoryTest {
    companion object {
        private val appDirs: AppDirs = AppDirsFactory.getInstance()
    }

    @Test
    fun testSame() {
        val newAppDirs = AppDirsFactory.getInstance()
        assertSame(appDirs, newAppDirs)
    }
}