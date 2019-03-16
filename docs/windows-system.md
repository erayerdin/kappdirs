# AppDirs Instance Methods

In a Windows machine, following `AppDirs` methods point at the directories
below:

```kotlin
val appDirs: AppDirs = AppDirsFactory.getInstance()
val APP_NAME = "myApp"
val APP_VERSION = "0.1.0"
val APP_AUTHOR = "myName" // or the organization name

val userLocalDataDir: String = appDirs.getUserDataDir(APP_NAME, APP_VERSION, APP_AUTHOR)
// C:\Users\ave\AppData\Local\myName\myApp\0.1.0

val userRoamingDataDir: String = appDirs.getUserDataDir(APP_NAME, APP_VERSION, APP_AUTHOR, roaming = true)
// C:\Users\ave\AppData\Roaming\myName\myApp\0.1.0

val userLocalConfigDir: String = appDirs.getUserConfigDir(APP_NAME, APP_VERSION, APP_AUTHOR)
// C:\Users\ave\AppData\Local\myName\myApp\0.1.0

val userRoamingConfigDir: String = appDirs.getUserConfigDir(APP_NAME, APP_VERSION, APP_AUTHOR, roaming = true)
// C:\Users\ave\AppData\Roaming\myName\myApp\0.1.0

val userCacheDir: String = appDirs.getUserCacheDir(APP_NAME, APP_VERSION, APP_AUTHOR)
// C:\Users\ave\AppData\Local\myName\myApp\Cache\0.1.0

val userLogDir: String = appDirs.getUserLogDir(APP_NAME, APP_VERSION, APP_AUTHOR)
// C:\Users\ave\AppData\Local\myName\myApp\Logs\0.1.0

val siteDataDir: String = appDirs.getSiteDataDir(APP_NAME, APP_VERSION, APP_AUTHOR)
// C:\ProgramData\myName\myApp\0.1.0

val siteConfigDir: String = appdirs.getSiteConfigDir(APP_NAME, APP_VERSION, APP_AUTHOR)
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
 > - Windows system is the only system that uses backward slashes (\\) as
 >   the directory separator.

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