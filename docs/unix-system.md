# AppDirs Instance Methods

In a Unix based or inspired machine, following `AppDirs` methods point at the
directories below:

```kotlin
val appDirs: AppDirs = AppDirsFactory.getInstance()
val APP_NAME = "myApp"
val APP_VERSION = "0.1.0"

val userDataDir: Path = appDirs.getUserDataDir(APP_NAME, APP_VERSION)
// /home/<username>/.local/share/myApp/0.1.0

val userConfigDir: Path = appDirs.getUserConfigDir(APP_NAME, APP_VERSION)
// /home/<username>/.config/myApp/0.1.0

val userCacheDir: Path = appDirs.getUserCacheDir(APP_NAME, APP_VERSION)
// /home/<username>/.cache/myApp/0.1.0

val userLogDir: Path = appDirs.getUserLogDir(APP_NAME, APP_VERSION)
// /home/<username>/.cache/myApp/logs/0.1.0

val siteLocalDataDir: Path = appDirs.getSiteDataDir(APP_NAME, APP_VERSION, local = true)
// /usr/local/share/myApp/0.1.0

val siteDataDir: Path = appDirs.getSiteDataDir(APP_NAME, APP_VERSION)
// /usr/share/myApp/0.1.0

val siteConfigDir: Path = appdirs.getSiteConfigDir(APP_NAME, APP_VERSION)
// /etc/myApp/0.1.0
```

 > <h4>Notes</h4>
 >
 > - Unix is the only system that utilizes `local` argument on
 >   `getSiteDataDir` and `getSiteConfigDir` methods.
 > - `roaming` argument on `getUserDataDir` and `getUserConfigDir` has no
 >   effect in Unix system.
 > - `appAuthor` argument on every method has no effect in Unix system.
 > - Unix system uses forward slashes (/) as the directory separator
 > - Instead of [harawata's appdirs](https://github.com/harawata/appdirs),
 >   `getSiteConfigDir` points directly under `/etc` instead of `/etc/xdg`.

# What is meant by "Unix"?

As in some parts of the document, it is meant *Unix based or inspired* by
"Unix" in this section. While GNU/Linux is not a true Unix or Unix-based
system, it is widely meant GNU/Linux system in this section of the whole
documentation, and, specifically, the ones that utilizes "XDG Base Directory
Specification".

# What does XDG Base Directory Specification mean?

 > hence the term "XDG-compliant"?

[XDG Base Directory Specification](https://specifications.freedesktop.org/basedir-spec/basedir-spec-latest.html)
is a documentation that builds up a couple of environment variables and
conventions on where the application-generated files should be kept.

Most GNU/Linux distributions, especially the popular ones, apply and rely on
these specifications (with minor differences). You, the developer, should be
safe in most environments. On the other hand, any BSD-based operating system
might choose to apply XDG Base Directory Specification or not.

`kappdirs` automatically assumes that the target Unix machine is already
XDG-compliant, but it does not call the specified environment variables,
instead it relies on `$HOME` or basic directory structure of most GNU/Linux
distributions in its `AppDirs` instance methods.

## How can I know a system is XDG-compliant, then?

If you've checked out [the documentation](https://specifications.freedesktop.org/basedir-spec/basedir-spec-latest.html),
you can programmatically check for the specified environment variables via
`System.getenv` and see if they return any value.

If you have enough evidence that the machine which your program is running
on is not XDG-compliant, then, most likely, you will not be able to use
`kappdirs` on that target machine properly.

If you think it should be implemented, you can [open an issue](https://github.com/erayerdin/kappdirs/issues),
send a PR of your implementation or, better for your time and my effort,
implement into your project privately and never share.