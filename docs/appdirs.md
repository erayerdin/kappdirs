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

# The Purpose of Directories

The application directories have different purposes. How they work also
differs on the operating system your application runs. You can see how they
differ on operating system sections, yet they also have a common convention
as well. In this section, we will discuss how they differ in common, but
keep in mind that these methods are based on a convention, which means you
can break the conventions any time you want, but it is just easier to keep
it alive.

## User *versus* Site Methods

The user application directory methods in `kappdirs` are as below:

 - `getUserDataDir`
 - `getUserConfigDir`
 - `getUserCacheDir`
 - `getUserLogDir`

The site application directory methods, on the other hand, are as below:

 - `getSiteDataDir`
 - `getSiteConfigDir`

User-based methods point at the directories which the user that is running
your application owns and/or has full access to read/write/execute by
default. So, naturally and usually, reading from, writing to or executing
the files in these directories will not create any problem or extra-care
for your application. You usually need to store data based on a particular
user in these directories.

On the other hand, site-based methods point at system-wide directories.
Your user might or might not have specific accesses to these directories.
You usually need to store system-wide data in these directories which will
naturally effect the other users in the same machine.
