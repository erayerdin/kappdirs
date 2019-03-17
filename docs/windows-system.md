# AppDirs Instance Methods

In a Windows machine, following `AppDirs` methods point at the directories
below:

```kotlin
val appDirs: AppDirs = AppDirsFactory.getInstance()
val APP_NAME = "myApp"
val APP_VERSION = "0.1.0"
val APP_AUTHOR = "myName" // or the organization name

val userLocalDataDir: Path = appDirs.getUserDataDir(APP_NAME, APP_VERSION, APP_AUTHOR)
// C:\Users\<username>\AppData\Local\myName\myApp\0.1.0

val userRoamingDataDir: Path = appDirs.getUserDataDir(APP_NAME, APP_VERSION, APP_AUTHOR, roaming = true)
// C:\Users\<username>\AppData\Roaming\myName\myApp\0.1.0

val userLocalConfigDir: Path = appDirs.getUserConfigDir(APP_NAME, APP_VERSION, APP_AUTHOR)
// C:\Users\<username>\AppData\Local\myName\myApp\0.1.0

val userRoamingConfigDir: Path = appDirs.getUserConfigDir(APP_NAME, APP_VERSION, APP_AUTHOR, roaming = true)
// C:\Users\<username>\AppData\Roaming\myName\myApp\0.1.0

val userCacheDir: Path = appDirs.getUserCacheDir(APP_NAME, APP_VERSION, APP_AUTHOR)
// C:\Users\<username>\AppData\Local\myName\myApp\Cache\0.1.0

val userLogDir: Path = appDirs.getUserLogDir(APP_NAME, APP_VERSION, APP_AUTHOR)
// C:\Users\<username>\AppData\Local\myName\myApp\Logs\0.1.0

val siteDataDir: Path = appDirs.getSiteDataDir(APP_NAME, APP_VERSION, APP_AUTHOR)
// C:\ProgramData\myName\myApp\0.1.0

val siteConfigDir: Path = appdirs.getSiteConfigDir(APP_NAME, APP_VERSION, APP_AUTHOR)
// C:\ProgramData\myName\myApp\0.1.0
```

 > <h4>Notes</h4>
 >
 > - `getUserDataDir` and `getUserConfigDir` without `roaming` or with
 >   `roaming` is `false` point at the same directory. (See `userLocalDataDir`
 >   and `userLocalConfigDir` above.)
 > - `getUserDataDir` and `getUserConfigDir` with `roaming` is `true` point
 >   at the same directory. (See `userRoamingDataDir` and
 >   `userRoamingConfigDir` above.)
 > - `getSiteDataDir` and `getSiteConfigDir` (with or without `local`
 >   argument) point at the same directory. (See `siteDataDir` and
 >   `siteConfigDir` above.)
 > - `local` argument on `getSiteDataDir` and `getSiteConfigDir` has no
 >   effect on Windows machines.
 > - Windows is the only system that utilizes `roaming` argument on
 >   `getUserDataDir` and `getUserConfigDir` methods.
 > - `local` argument on `getSiteDataDir` and `getSiteConfigDir` has no effect
 >   in Windows system.
 > - Windows is the only system that utilizes `appAuthor` on every method.
 > - Windows system is the only system that uses backward slashes (\\) as
 >   the directory separator.
 
## Note on `appAuthor`

Windows is the only system that uses `appAuthor` argument. That's why, in all
`AppDirs` instance methods' signatures, it is marked as `String?` and defaults
to `null`.

Since it is `null` by default, you do not have to pass `appAuthor`. In this
case, `appAuthor` will not be rendered to `Path` instance. See the examples
below and compare them:

```kotlin
val userLocalDataDirWithAppAuthor: Path = appDirs.getUserDataDir(APP_NAME, APP_VERSION, APP_AUTHOR)
// C:\Users\<username>\AppData\Local\myName\myApp\0.1.0

val userLocalDataDirWithAppAuthor: Path = appDirs.getUserDataDir(APP_NAME, APP_VERSION)
// C:\Users\<username>\AppData\Local\myApp\0.1.0
```

# What is Roaming?

Since Windows NT 3.1, Windows system uses a concept called *roaming*, which
moves or copies the content of the related application directory over the
network to different machines.

The methods below support one extra argument called `roaming`:

 - `getUserDataDir`
 - `getUserConfigDir`

With `roaming` is `true`, the application directory points at the roaming
application directory.

However, since this option is not widely used in Windows, it is not activated
by default. To activate this feature, see [this documentation](https://docs.microsoft.com/en-us/windows-server/storage/folder-redirection/deploy-roaming-user-profiles)
of Microsoft.

This feature is especially useful for business machines that are connected
to the same network.