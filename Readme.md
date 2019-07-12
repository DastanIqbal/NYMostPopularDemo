# Kotlin+Retrofit+Coroutines Demo with Test

This app just shows how to use retrofit and coroutines with Android Lifecycle


## Machine Used
- Android Studio v3.4.2
- Gradle v5.1.1
- Android SDK API 29
- Ubuntu 19.04

## Build 
Import the project in Android Studio File->New->Import Project-><Select the project> NYMostPopular

Create keys.properties in root folder(NYMostPopular)
```
NYT_APIKEY_DEV="NY Dev API Key"
NYT_APIKEY_PROD="NY Prod API Key"
```

Let the AS build and download all dependencies or Use Build->Rebuild Project
Use Shift+F10 or Run->Run to Run the app in device/emulator

Make sure you [enabled adb debugging][enable-adb] on your device(s). 
[enable-adb]: https://developer.android.com/studio/command-line/adb.html#Enabling

## Run Test
#### Unit Test
Select ```test/java/com/nyt/mostpopular/RepositoryTest``` Right Click-> Run RepsoitoryTest

#### Instrumentation Test
Select ```androidTest/java/com/nyt/mostpopular/mostviewed/MostViewedFragmentTest``` Right Click-> Run MostViewedFragmentTest






