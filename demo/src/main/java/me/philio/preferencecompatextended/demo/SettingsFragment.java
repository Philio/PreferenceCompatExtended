package me.philio.preferencecompatextended.demo;

import android.os.Bundle;

import me.philio.preferencecompatextended.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
    }

}
