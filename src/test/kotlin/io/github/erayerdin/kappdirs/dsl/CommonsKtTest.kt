package io.github.erayerdin.kappdirs.dsl

import org.junit.Assert
import org.junit.Test

class CommonsKtTest {
    @Test
    fun testApp() {
        val application = App("foo", "0.1.0", "eray")
        val flags: Set<Flag> = setOf()

        app(application, flags) { app, flags ->
            Assert.assertEquals("foo", app.appName)
            Assert.assertTrue(flags.isEmpty())
        }
    }
}