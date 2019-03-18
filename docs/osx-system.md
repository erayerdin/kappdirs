# Mac OS X System

## AppDirs Instance Methods

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

val userDownloadsDir: Path = appDirs.getUserDownloadsDir()
// /Users/<username>/Downloads

val userDesktopDir: Path = appDirs.getUserDesktopDir()
// /Users/<username>/Desktop

val userDocumentsDir: Path = appDirs.getUserDocumentsDir()
// /Users/<username>/Documents

val userMusicDir: Path = appDirs.getUserMusicDir()
// /Users/<username>/Music

val userPicturesDir: Path = appDirs.getUserPicturesDir()
// /Users/<username>/Pictures

val userVideosDir: Path = appDirs.getUserVideosDir()
// /Users/<username>/Videos

val siteDataDir: Path = appDirs.getSiteDataDir(APP_NAME, APP_VERSION)
// /Library/Application Support/myApp/0.1.0

val siteConfigDir: Path = appdirs.getSiteConfigDir(APP_NAME, APP_VERSION)
// /Library/Application Support/myApp/0.1.0
```

!!! note
    - Despite being a Unix-based system, Mac OS X uses its own conventions
      for application directories, hence it is not XDG-compliant.
    - `getUserDataDir` and `getUserConfigDir` point at the same directory.
      (See `userDataDir` and `userConfigDir` above.)
    - `getSiteDataDir` and `getSiteConfigDir` point at the same directory.
      (See `siteDataDir` and `siteConfigDir` above.)
    - `roaming` argument on `getUserDataDir` and `getUserConfigDir` has no
      effect in Mac OS X system.
    - `local` argument on `getSiteDataDir` and `getSiteConfigDir` has no effect
      in Mac OS X system.
    - `appAuthor` argument on every method has no effect in Mac OS X system,
      hence it is not used in the examples.
    - Mac OS X system uses forward slashes (/) as the directory separator.

!!! warning
    Since the lack of knowledge on Mac OS X of the developer of this library,
    one possible bug here is any Mac OS X system that uses another language
    other than `en_US`. Most XDG-compliant Unix based/inspired systems
    provide localized values of extra directories in `$HOME/.config/user-dirs.dirs`
    file but there's no clear knowledge of the developer what happens if
    another language used in Mac OS X. You can either inform how Mac OS X
    extra directories named and discuss how this problem can be solved in
    [this issue](https://github.com/erayerdin/kappdirs/issues/13) or you can
    also provide your own pull-request (the problematic parts are presented
    with todos in the codebase).