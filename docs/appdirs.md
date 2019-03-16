# `AppDirs` Instance Methods

Every `AppDirs` instance contains the methods below:

 - `getUserDataDir`
 - `getUserConfigDir`
 - `getUserCacheDir`
 - `getUserLogDir`
 - `getSiteDataDir`
 - `getSiteConfigDir`

Each method's signature contains the arguments below:

| Name | Type |
|------|------|
| appName | String |
| appVersion | String |
| appAuthor | String |

As an example:

```kotlin
val appDirs: AppDirs = AppDirsFactory.getInstance()
val APP_NAME = "myApp"
val APP_VERSION = "0.1.0"
val APP_AUTHOR = "myName" // or the organization name

val userDataDir: String = appDirs.getUserDataDir(APP_NAME, APP_VERSION, APP_AUTHOR)
val userConfigDir: String = appDirs.getUserConfigDir(APP_NAME, APP_VERSION, APP_AUTHOR)
val userCacheDir: String = appDirs.getUserCacheDir(APP_NAME, APP_VERSION, APP_AUTHOR)
val userLogDir: String = appDirs.getUserLogDir(APP_NAME, APP_VERSION, APP_AUTHOR)
val siteDataDir: String = appDirs.getSiteDataDir(APP_NAME, APP_VERSION, APP_AUTHOR)
val siteConfigDir: String = appdirs.getSiteConfigDir(APP_NAME, APP_VERSION, APP_AUTHOR)
```

Apart from the arguments above, `getUserDataDir` and `getUserConfigDir` methods
(first two above) have one extra argument called `roaming`, which is `false` by
default. As an example:

```kotlin
// roaming examples
val userDataDir: String = appDirs.getUserDataDir(APP_NAME, APP_VERSION, APP_AUTHOR, true)
val userConfigDir: String = appDirs.getUserConfigDir(APP_NAME, APP_VERSION, APP_AUTHOR, true)
```

Also, `getSiteDataDir` and `getSiteConfigDir` methods (last two above) have
one extra argument called `local`, which is`false` by default. As an example:

```kotlin
// local examples
val siteDataDir: String = appDirs.getSiteDataDir(APP_NAME, APP_VERSION, APP_AUTHOR, true)
val siteConfigDir: String = appdirs.getSiteConfigDir(APP_NAME, APP_VERSION, APP_AUTHOR, true)
```