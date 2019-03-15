package io.github.erayerdin.kappdirs.appdirs

internal val HOME_DIR = System.getProperty("user.home")
internal val SEPARATOR = System.getProperty("file.separator")

// Windows Only
internal val APPDATA = System.getenv("APPDATA")
internal val LOCALAPPDATA = System.getenv("LOCALAPPDATA")
internal val PROGRAMDATA = System.getenv("PROGRAMDATA")