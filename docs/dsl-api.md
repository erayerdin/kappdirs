# DSL API

## Introduction

Kotlin provides many syntactic sugars called DSL API. `kappdirs` contains a DSL
API as well, which will make your development easier. First, you need to create
an `AppDir` instance:

```kotlin
val APP_NAME = "foo"
val APP_VERSION = "0.1.0"
val APP_AUTHOR = "myName" // or related organization name

val appDir: AppDir = AppDir(APP_NAME, APP_VERSION, APP_AUTHOR)
```

!!! warning
    Mind the name `AppDir`. It is a different class located under
    `io.github.erayerdin.kappdirs.dsl`.

Now you can use DSL API and write/read application files easily. See the
example:

```kotlin
appDir {
    userData("my", "config", "file.txt") { root, parent, file ->
    // `root` points at root directory of `userData`
    // `parent` points at the directory which contains the `file`
        file.writeText("Hello, world!")
    }
}
```

This will create `my/config` directory and `file.txt` file inside the target
system's application user data directory *and* write `Hello, world!` into that
file. `writeText` is a Kotlin extension for `File` object, to get more info,
[see official Kotlin documentation](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.io/java.io.-file/index.html).

!!! note
    `root`, `parent` and `file` arguments above are `File` objects instead of
    `Path` objects in order to make it easier to check presence, permissions
    and execute IO operations.

You can use methods below inside your `AppDir` instance:

 - `userData`
 - `userConfig`
 - `userConfig`
 - `userLog`
 - `userDownloads`
 - `userDesktop`
 - `userDocuments`
 - `userMusic`
 - `userPictures`
 - `userVideos`
 - `siteData`
 - `siteConfig`

They use the same arguments, just `"path", "to", "your", "file.txt"`.

## System-Specific Arguments

As it is stated in [Windows System](windows-system.md) and [Unix System](unix-system.md)
sections, these systems can take arguments that are specific to them.

### `roaming` on Windows

`userData` and `userConfig` can take extra `roaming` argument (which is `false`
by default) that only has effect on Windows.

```kotlin
appDir {
    userData("bar", "baz.txt", roaming = true) { root, parent, file ->
        // here, `root`, `parent` and `file` point at specific roaming dir on windows
    }
    
    // same goes for userConfig
}
```

### `local` on Linux

`siteData` can take extra `local` argument (which is `false` by default) that
only has effect on Linux.

```kotlin
appDir {
    siteData("config.txt", local = true) { root, parent, file ->
        // here, `root`, `parent` and `file` point under `/usr/local`
    }
}
```

## On Presence and Permissions

Since different systems has different security implementations, `kappdirs`
does not try to create or write into the directories or file you have
provided. See the example below:

```kotlin
appDir {
    userData("save1.txt") { root, parent, file -> 
        // `root`, `parent` and `file` do not exist.
    }
}
```

So, you have to check the permissions and create them before you do any IO
operations.

```kotlin
appDir {
    userData("saves", "save1.txt") { root, parent, file ->
        parent.mkdirs() // create directories recursively
        
        if (!file.exists()) {
            file.createNewFile()
        }
        
        // now do IO-related operations
    }
}
```

## On Extra Application Directories

Methods below are called *Extra Application Directories* since they are not
directly related to the application:

 - `userDownloads`
 - `userDesktop`
 - `userDocuments`
 - `userMusic`
 - `userPictures`
 - `userVideos`

The only thing that you need to know about these methods is that these do
not utilize `appName`, `appVersion` or `appAuthor` in `AppDir` instance.
Consider the example:

```kotlin
appDirs {
    userDownloads("myApp", "file.txt") { root, parent, file -> 
        // considering that you are on a unix based/inspired system
        // and your system locale is set to en_US
        val rootFilePath: String = root.toString()
        // /home/<username>/Downloads
        
        val parentFilePath: String = parent.toString()
        // /home/<username>/Downloads/myApp
        
        val fileFilePath: String = file.toString()
        // /home/<username>/Downloads/myApp/file.txt
    }
}
```

It is intended this way because there were no clear conventions about how to
store files under these directories.