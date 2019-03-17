package io.github.erayerdin.kappdirs.dsl

import org.junit.Assert
import org.junit.Test

class CommonsKtTest {
    @Test
    fun testApp() {
        val application = App("foo", "0.1.0", "eray")

        app(application) {
            Assert.assertEquals("foo", it.appName)
        }
    }
}