# AppDirs Instance Methods

In a Mac OS X machine, following `AppDirs` methods point at the directories
below:

```kotlin
val appDirs: AppDirs = AppDirsFactory.getInstance()
val APP_NAME = "myApp"
val APP_VERSION = "0.1.0"

val userDataDir: Path = appDirs.getUserDataDir(APP_NAME, APP_VERSION)
// /Users/<username>/Library/Application Support/myApp/0.1.0

val userConfigDir: Path = appDirs.getUserConfigDir(APP_NAME, APP_VERSION)
// /Users/<username>/Library/Application Support/myApp/0.1.0

val userCacheDir: Path = appDirs.getUserCacheDir(APP_NAME, APP_VERSION)
// /Users/<username>/Library/Caches/myApp/0.1.0

val userLogDir: Path = appDirs.getUserLogDir(APP_NAME, APP_VERSION)
// /Users/<username>/Library/Logs/myApp/0.1.0

val siteDataDir: Path = appDirs.getSiteDataDir(APP_NAME, APP_VERSION)
// /Library/Application Support/myApp/0.1.0

val siteConfigDir: Path = appdirs.getSiteConfigDir(APP_NAME, APP_VERSION)
// /Library/Application Support/myApp/0.1.0
```

 > <h4>Notes</h4>
 >
 > - Despite being a Unix-based system, Mac OS X uses its own conventions
 >   for application directories, hence it is not XDG-compliant.
 > - `getUserDataDir` and `getUserConfigDir` point at the same directory.
 >   (See `userDataDir` and `userConfigDir` above.)
 > - `getSiteDataDir` and `getSiteConfigDir` point at the same directory.
 >   (See `siteDataDir` and `siteConfigDir` above.)
 > - `roaming` argument on `getUserDataDir` and `getUserConfigDir` has no
 >   effect in Mac OS X system.
 > - `local` argument on `getSiteDataDir` and `getSiteConfigDir` has no effect
 >   in Mac OS X system.
 > - `appAuthor` argument on every method has no effect in Mac OS X system.
 > - Mac OS X system uses forward slashes (/) as the directory separator.