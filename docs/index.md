# kappdirs

[![JitPack](https://img.shields.io/jitpack/v/erayerdin/kappdirs.svg?label=version&logo=kotlin&logoColor=ffffff&style=flat-square)][jitpack_url]
![License](https://img.shields.io/badge/license-Apache%20License%203.0-cccccc.svg?style=flat-square)
[![Telegram](https://img.shields.io/badge/telegram-%40erayerdin-%2332afed.svg?style=flat-square&logo=telegram&logoColor=white)](https://t.me/erayerdin)

[jitpack_url]: https://jitpack.io/#erayerdin/kappdirs

kappdirs is an appdirs implementation written in pure Kotlin. It has a similar
implementation of [harawata's appdirs](https://github.com/harawata/appdirs)
and inspired by that project.

# Installation

You can get installation instruction from [Jitpack][jitpack_url].

# A Brief Introduction

You can grab an instance of `AppDirs` as below:

```kotlin
val appDirs: AppDirs = AppDirsFactory.getInstance()
val appData: Path = appDirs.getUserDataDir("myApp", "0.1.0", "myName")
```

`AppDirsFactory::getInstance` will create an `AppDirs` object based on your
operating system and then you can use one of its methods to get `Path`
instance.

Currently, the operating systems below are supported:

 - Windows
 - Mac OS X
 - Unix (or Unix based/inspired)

 > <h4>Note</h4>
 >
 > An `AppDirs` instance is created lazily, which means it is created by
 > `AppDirsFactory` only once and when `getInstance` called and later on
 > you get the reference to the same object.