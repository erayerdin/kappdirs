# AppDirs Instance

To get an `AppDirs` instance, you need to get it from
`AppDirsFactory::getInstance`. See example below:

```kotlin
val appDirs: AppDirs = AppDirsFactory.getInstance()
```

!!! note
    Since it is assumed that the operating system cannot be changed during
    the runtime, `AppDirsFactory::getInstance` will lazily instantiate an
    `AppDirs` instance, which means the second time you call it, you will
    get the reference to the same object.

## Methods

Every `AppDirs` instance contains the methods and their signatures below:
 
```kotlin
getUserDataDir(
    appName: String,
    appVersion: String,
    appAuthor: String? = null,
    roaming: Boolean = false
): Path
getUserConfigDir(
    appName: String,
    appVersion: String,
    appAuthor: String? = null,
    roaming: Boolean = false
): Path
getUserCacheDir(
    appName: String,
    appVersion: String,
    appAuthor: String? = null
): Path
getUserLogDir(
    appName: String,
    appVersion: String,
    appAuthor: String? = null
): Path
getUserDownloadsDir(): Path
getUserDesktopDir(): Path
getUserDocumentsDir(): Path
getUserMusicDir(): Path
getUserPicturesDir(): Path
getUserVideosDir(): Path
getSiteDataDir(
    appName: String, 
    appVersion: String, 
    appAuthor: String? = null, 
    local: Boolean = false
): Path
getSiteConfigDir(
    appName: String, 
    appVersion: String, 
    appAuthor: String? = null
): Path
```

As an example:

```kotlin
val appDirs: AppDirs = AppDirsFactory.getInstance()
val APP_NAME = "myApp"
val APP_VERSION = "0.1.0"
val APP_AUTHOR = "myName" // or the organization name

val userDataDir: Path = appDirs.getUserDataDir(APP_NAME, APP_VERSION, APP_AUTHOR)
val userConfigDir: Path = appDirs.getUserConfigDir(APP_NAME, APP_VERSION, APP_AUTHOR)
val userCacheDir: Path = appDirs.getUserCacheDir(APP_NAME, APP_VERSION, APP_AUTHOR)
val userLogDir: Path = appDirs.getUserLogDir(APP_NAME, APP_VERSION, APP_AUTHOR)
val userDownloadsDir: Path = appDirs.getUserDownloadsDir()
val userDesktopDir: Path = appDirs.getUserDesktopDir()
val userDocumentsDir: Path = appDirs.getUserDocumentsDir()
val userMusicDir: Path = appDirs.getUserMusicDir()
val userPicturesDir: Path = appDirs.getUserPicturesDir()
val userVideosDir: Path = appDirs.getUserVideosDir()
val siteDataDir: Path = appDirs.getSiteDataDir(APP_NAME, APP_VERSION, APP_AUTHOR)
val siteConfigDir: Path = appdirs.getSiteConfigDir(APP_NAME, APP_VERSION, APP_AUTHOR)
```

`getUserDataDir` and `getUserConfigDir` methods (first two above) have one
extra argument called `roaming`, which is `false` by default. As an example:

```kotlin
// roaming examples
val userDataDir: Path = appDirs.getUserDataDir(APP_NAME, APP_VERSION, APP_AUTHOR, roaming = true)
val userConfigDir: Path = appDirs.getUserConfigDir(APP_NAME, APP_VERSION, APP_AUTHOR, roaming = true)
```

!!! note
    Practically, `roaming` argument only has effect on Windows machines.
    To get more information about `roaming`, check out [this part](windows-system.md#what-is-roaming)
    of Windows section of the documentation.

Also, `getSiteDataDir` method has one extra argument called `local`, which
is`false` by default. As an example:

```kotlin
// local example
val siteDataDir: Path = appDirs.getSiteDataDir(APP_NAME, APP_VERSION, APP_AUTHOR, local = true)
```

!!! note
    Practically, `local` argument only has effect on [XDG-compliant](http://standards.freedesktop.org/basedir-spec/basedir-spec-latest.html)
    Unix based or inspired systems (including XDG-compliant Linux). To get
    more information about `local`, see [Unix section](unix-system.md) of
    the documentation.

## The Purpose of Directories

The application directories have different purposes. How they work also
differs on the operating system your application runs. You can see how they
differ on operating system sections, yet they also have a common convention
as well. In this section, we will discuss how they differ in common, but
keep in mind that these methods are based on a convention, which means you
can break the conventions any time you want, but it is just easier to keep
it alive.

### User <em>versus</em> Site Methods

The user application directory methods in `kappdirs` are as below:

 - `getUserDataDir`
 - `getUserConfigDir`
 - `getUserCacheDir`
 - `getUserLogDir`
 - `getUserDownloadsDir`
 - `getUserDesktopDir`
 - `getUserDocumentsDir`
 - `getUserMusicDir`
 - `getUserPicturesDir`
 - `getUserVideosDir`

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
Your user might or might not have specific permissions to these directories.
You usually need to store system-wide data in these directories which will
naturally effect the other users in the same machine.

### Data <em>versus</em> Config Methods

The data application directory methods in `kappdirs` are as below:

 - `getUserDataDir`
 - `getSiteDataDir`

And the config application directory methods are as below:

 - `getUserConfigDir`
 - `getSiteConfigDir`

The distinction in the names are clear. Data application directories contain
the data for your application while config directories contain the config
files.

To give an example, consider a game. In this context, `getUserDataDir`
method points to the directory where the current user's save files are
stored while `getUserConfigDir` method points to the directory how the user
wants the game to behave, like the resolution for the game or user-specific
control-schemes.

!!! note
    Also, in this example, you do not need `getSiteDataDir` or
    `getSiteConfigDir` method. You usually do not need to use these methods,
    especially in *writing* operations. It is a good practice to write into
    system-wide (site) directories on *installation* process of your
    application and with *proper permissions* and then, you do *reading*
    operations from these directories.

### Cache Method

Cache directory can be acquired via `getUserCacheDir` method. It is used to
store the files that is acquired after a heavy operation.

For example, network operations are considered heavy operations since you
need to write to and read from socket in low level. If your application
downloads an asset such as an image, a video or an audio from the internet,
it does not need to download these again in the next operation. Instead, it
can save these assets into cache directory and read them on.

Another example is where you need to generate an image programmatically.
Imagine a scenario where you detect the face and properly crop for a
profile picture. Instead of doing it again and again and wasting CPU/GPU
resources, you can save the image to the cache directory and use it in the
next run.

Cache directory is only a user directory because users create caches and
you cannot always rely on caches (meaning that they are not intended to be
persistent data). For example, if the cached resource that your program is
looking for is not in its place, you will likely need to *redownload* or
*regenerate* and, in the end, *recache* your resource.

### Log Method

Log directory can be acquired via `getUserLogDir` method. It is used to
dump the logs that your application generates.

Logging is a good practice to debug your application on different machines
and, in case of an issue, you can easily grab the log files and look for
the issue.

Log directory is only a user directory. Keep in mind that you might need to
clear the older logs from time to time in order not to bloat the drive of
the machine that your application runs on.

### Extra User Application Directories

Extra user application directories are new user-based directories that you
can use. These methods are as below:

 - `getUserDownloadsDir`
 - `getUserDesktopDir`
 - `getUserDocumentsDir`
 - `getUserMusicDir`
 - `getUserPicturesDir`
 - `getUserVideosDir`
 
The common point that these methods share is that they require zero
argument, which means you can use one of them as below:

```kotlin
val downloadsDir: Path = appDirs.getUserDownloadsDir()
```

That is intended because there is no convention about how to use or utilize
these directories, so if you want to use your application name as a child,
you should do it manually as below:

```kotlin
val appDownloadDir: Path = Paths.get(downloadsDir, "myApp")
```