# PreferenceCompatExtended

## About

An extension of the Preference support library that includes a NumberPicker dialog and various fixes.

Currently works well and has been tested on a wide variety of SDK 14+ devices/emulators, better support for earlier versions will be added later.

Current minimum SDK is 11 due to align with NumberPicker, this will be changed to a back ported version in a future version to support 7+.

Contributions are very welcome, please submit a pull request.

## Download

### Gradle

Add the following to your `build.gradle`:

    compile 'me.philio:preferencecompatextended:0.2.0'
    
The latest v7 and v14 preference libraries are included and are not required
    
## Usage

### New project

Create a fragment that extends the included `PreferenceFragmentCompat`

### Existing project

Replace imported `android.support.v7.preference.PreferenceFragmentCompat` with `me.philio.preferencecompatextended.PreferenceFragmentCompat`

## Custom preferences

Note: Full package name is not required as the preferences are in the same package as the preference support library, Android Studio produces a warning but your project will build and run without issue.

### NumberPickerPreference

#### Add to XML

    <NumberPickerPreference
        android:defaultValue="1"
        android:key="number_picker"
        android:title="A numeric preference"
        app:maxValue="100"
        app:minValue="1"
        app:valueAsSummary="true" />
        
#### Custom attributes

* `minValue` - The minimum value of the number picker
* `maxValue` - The maximum value of the number picker
* `valueAsSummary` - When `true`, sets the current value as the summary text
* `descendantFocusable` - When `false`, sets descendant focusability to `FOCUS_BLOCK_DESCENDANTS` preventing the EditText from gaining focus

## Styling

There are currently a number of bugs in the Google library, using the provided `PreferenceTheme` parent styles for your settings screen should provide a consistent experience on SDK 14+ devices and fallback styles on earlier versions.

Some of the styling fixes were taken from or inspired by: https://github.com/Gericop/Android-Support-Preference-V7-Fix

## License

Copyright 2015 Phil Bayfield  
Copyright (C) 2015 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

&nbsp;&nbsp;&nbsp;&nbsp;http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.