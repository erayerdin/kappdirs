package io.github.erayerdin.kappdirs.appdirs

import java.nio.file.Path
import java.nio.file.Paths

internal class UnixAppDirs: AppDirs {
    override fun getUserDataDir(appName: String, appVersion: String, appAuthor: String?, roaming: Boolean): Path {
        return Paths.get(
            HOME_DIR,
            arrayOf(".local", "share", appName, appVersion).joinToString(SEPARATOR)
        )
    }

    override fun getUserConfigDir(appName: String, appVersion: String, appAuthor: String?, roaming: Boolean): Path {
        return Paths.get(
            HOME_DIR,
            arrayOf(".config", appName, appVersion).joinToString(SEPARATOR)
        )
    }

    override fun getUserCacheDir(appName: String, appVersion: String, appAuthor: String?): Path {
        return Paths.get(
            HOME_DIR,
            arrayOf(".cache", appName, appVersion).joinToString(SEPARATOR)
        )
    }

    override fun getUserLogDir(appName: String, appVersion: String, appAuthor: String?): Path {
        return Paths.get(
            HOME_DIR,
            arrayOf(".cache", appName, "logs", appVersion).joinToString(SEPARATOR)
        )
    }

    override fun getUserDownloadsDir(): Path {
        return XdgUserDirs.XDG_DOWNLOAD_DIR.getPath()
    }

    override fun getUserDesktopDir(): Path {
        return XdgUserDirs.XDG_DESKTOP_DIR.getPath()
    }

    override fun getUserDocumentsDir(): Path {
        return XdgUserDirs.XDG_DOCUMENTS_DIR.getPath()
    }

    override fun getUserMusicDir(): Path {
        return XdgUserDirs.XDG_MUSIC_DIR.getPath()
    }

    override fun getUserPicturesDir(): Path {
        return XdgUserDirs.XDG_PICTURES_DIR.getPath()
    }

    override fun getSiteDataDir(appName: String, appVersion: String, appAuthor: String?, local: Boolean): Path {
        val directories: Array<String> = if (local) {
            arrayOf("usr", "local", "share", appName, appVersion)
        } else {
            arrayOf("usr", "share", appName, appVersion)
        }

        return Paths.get("/", directories.joinToString(SEPARATOR))
    }

    override fun getSiteConfigDir(appName: String, appVersion: String, appAuthor: String?): Path {
        return Paths.get("/", arrayOf("etc", appName, appVersion).joinToString(SEPARATOR))
    }
}

private val USER_DIR_CONFIG_FILE = Paths.get(HOME_DIR, ".config", "user-dirs.dirs").toFile()

/**
 * Supported keys and values for XDG-compliant Unix systems.
 */
private enum class XdgUserDirs(private val defaultPath: Path) {
    XDG_DESKTOP_DIR(Paths.get(HOME_DIR, "Desktop")),
    XDG_DOWNLOAD_DIR(Paths.get(HOME_DIR, "Downloads")),
    XDG_DOCUMENTS_DIR(Paths.get(HOME_DIR, "Documents")),
    XDG_MUSIC_DIR(Paths.get(HOME_DIR, "Music")),
    XDG_PICTURES_DIR(Paths.get(HOME_DIR, "Pictures")),
    XDG_VIDEOS_DIR(Paths.get(HOME_DIR, "Videos"));

    /**
     * A property which holds lazy value which will not change on runtime once initialized.
     */
    private var path: Path? = null

    /**
     * @return Path for the enum key.
     */
    fun getPath(): Path {
        if (path != null) {
            return path as Path
        }

        if (!USER_DIR_CONFIG_FILE.exists()) {
            return defaultPath
        }

        USER_DIR_CONFIG_FILE.readLines().forEach {
            val trimmedLine = it.trim()

            when {
                trimmedLine.startsWith(this.name) -> {
                    path = parsePathFromLine(trimmedLine)
                }
                else -> {
                    path = defaultPath
                }
            }
        }

        return path as Path
    }

    /**
     * Reads and parses line from the XDG app dirs configurations file.
     *
     * @return Path of key in the configuration file.
     */
    private fun parsePathFromLine(line: String): Path {
        var value: String = line.split('=')[1]

        value = value.drop(1)
        value = value.dropLast(1)

        val parsedPath: ArrayList<String> = value.split(SEPARATOR) as ArrayList<String>
        parsedPath.remove("\$HOME")

        return Paths.get(HOME_DIR, *parsedPath.toTypedArray())
    }
}