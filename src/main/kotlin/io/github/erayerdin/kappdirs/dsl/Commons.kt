package io.github.erayerdin.kappdirs.dsl

data class App(
    val appName: String,
    val appVersion: String,
    val appAuthor: String? = null
)

enum class Flag {
    ROAMING, LOCAL
}

fun app(app: App, method: (App) -> Unit) {
    method(app)
}