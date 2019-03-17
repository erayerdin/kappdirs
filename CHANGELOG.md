# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.2.0-alpha] - 2019-03-18
### Added
 - New DSL API
 - Documentation for DSL API

### Removed
 - *Recipes* section in documentation

## [0.1.5-alpha] - 2019-03-17
### Changed
 - Moved `AppDirs` interface from package root to `appdirs` package
 - Now all `AppDirs` instance methods return `Path` instead of `String`
 - `appAuthor` argument on `AppDirs` methods is now `String?` instead of `String`
 - Documentation related to changes
 - Added *Recipes > Permissions* section to the documentation

### Removed
 - `local` argument on `AppDirs::getSiteConfigDir`

## [0.1.4-prealpha] - 2019-03-16
### Added
 - Docstrings

## [0.1.3-SNAPSHOT] - 2019-03-16
### Added
 - Documentation

## [0.1.2-SNAPSHOT] - 2019-03-16
### Added
 - Support for Windows.

## [0.1.1-SNAPSHOT] - 2019-03-15
### Added
 - Support for OSX.

## [0.1.0-SNAPSHOT] - 2019-03-15
### Added
 - Support for *nix based systems (exluding OSX).