# Android Common Utils
__An effort to reduce re-writing similar codes across projects.__  
  
common-utils-android contains some of the commonly used android functions.

## Utilities
- [LocalityHelpers](.commonutils/src/main/java/com/asgatech/commonutils/LocalityHelper.kt) - Help to set and update locale of the app.
- [PermissionHelpers](./commonutils/src/main/java/com/asgatech/commonutils/permissions/PermissionHandler.kt) - Making it easy to ask and check for app specific permissions.
- [ImageLoadingHelpers](./commonutils/src/main/java/com/asgatech/commonutils/ImageLoaderHelper.kt) - Used to handle the image loading in the app (Url Image, Bitmap or Drawable resource).
- [ScreenHelpers](./commonutils/src/main/java/com/asgatech/commonutils/ScreenHelpers.kt) -Obtain the device height and width, also helping to set activity screen to full screen.
- [WebViewHelpers](./commonutils/src/main/java/com/asgatech/commonutils/WebViewHelper.kt) -Handle all webview operations (Seting html text in specific language) 

## Install and use
### Requirements
API 21+

### 1. Using Jitpack
* Add the JitPack maven repository to your project level build.gradle file:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
        ...
    }
}
```

* Add this line inside the dependency block of your module level build.gradle file:
```
 implementation 'com.github.asgatech20:CommonUtils:$version'
```
Replace `$version` with the latest version of common-utils. You can find a complete list of releases on the [releases](https://github.com/asgatech20/CommonUtils/releases) page.

### Happy Coding

## Authors

* [Muhammad Noamany](https://github.com/muhammadnomany25)
* [Ibrahim Ali](https://github.com/IbrahimAli2017)
* [Ahmed Saber](https://github.com/Ahmed-Saber-25)

## Owner

* [AsgaTech Company](https://github.com/asgatech20)


### License

    Copyright 2021 AsgaTech Company.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

