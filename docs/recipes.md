# Permissions

It is considered a good practice to check the permissions before you perform an
operation on disk. Since `AppDirs` instance's methods all return `Path` object,
you can use `Files::is*` methods to check out for proper permissions. Consider
the example below:

```kotlin
// considering you already have AppDirs instance named appDirs

val siteDataDir: Path = appDirs.getSiteDataDir("myApp", "0.1.0")
// /usr/share/myApp/0.1.0

val canWrite: Boolean = Files.isWritable(siteDataDir)
// false
// which means we cannot write to this directory
```