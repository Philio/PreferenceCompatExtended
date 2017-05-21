# PreferenceCompatExtended

## About

An extension of the Preference support library that originally fixed various bugs found in early versions (now all resolved). Currently includes a customisable NumberPickerPreference and TimePickerPreference. 

![](https://raw.githubusercontent.com/Philio/PreferenceCompatExtended/master/side-by-side.png)

Minimum SDK is 11 to align with NumberPicker.

Contributions are welcome, please submit a pull request.

## Download

### Gradle

Add the following to your `build.gradle`:

    compile 'me.philio:preferencecompatextended:1.2.1'
    
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
* `valueAsSummary` - When `true`, sets the current value as the summary text, optionally formatted using `summaryFormat` if set
* `summaryFormat` - String format to use as the summary text
* `descendantFocusable` - Set the descendantFocusability of the number picker
* `wrapSelectorWheel` - Sets whether the selector wheel should wrap around the minimum and maximum values
* `subtitleText` - Set the subtitle text, shown to the right of the number picker in the dialog

### TimePickerPreference

#### Add to XML

    <TimePickerPreference
        android:key="time_picker"
        android:title="A time picker preference"
        app:defaultHours="10"
        app:defaultMins="0"
        app:timeAsSummary="true" />

#### Custom attributes

* `timeAsSummary` - When `true`, sets the currently selected time as the summary text (default)
* `timeFormat` - Time formatting method, either `inherit` to use system setting or `hours12`/`hours24` for 12 and 24 hour settings, automatically set to `custom` when `timeFormatString` is set
* `timeFormatString` - Format string to be passed to `SimpleDateFormat`, overrides `timeFormat` if set
* `defaultHours` - Set the default value in hours, requires `defaultMins`
* `defaultMins` - Set the default value in minutes, requires `defaultHours`
* `dialogTimeFormat` - Set the dialog 24 hour setting, either `inherit` to use system setting or `hours12`/`hours24` for 12 and 24 hour settings

## Styling

In your theme just set the `preferenceTheme` to the provided `PreferenceTheme`:

    <item name="preferenceTheme">@style/PreferenceTheme</item>
    
For SDK 11+ devices this inherits from `PreferenceThemeOverlay`.

For SDK 14+ devices this inherits from `PreferenceThemeOverlay.v14.Material`.

## License

Copyright 2015-2017 Phil Bayfield  
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