package me.philio.numberpickerpreference.demo;

import android.os.Bundle;

import me.philio.numberpickerpreference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
    }

}
