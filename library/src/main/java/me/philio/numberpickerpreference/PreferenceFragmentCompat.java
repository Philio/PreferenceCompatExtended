package me.philio.numberpickerpreference;

import android.support.v4.app.DialogFragment;
import android.support.v7.preference.NumberPickerPreference;
import android.support.v7.preference.NumberPickerPreferenceDialogFragmentCompat;
import android.support.v7.preference.Preference;

public abstract class PreferenceFragmentCompat extends android.support.v7.preference.PreferenceFragmentCompat {

    private static final String DIALOG_FRAGMENT_TAG = "android.support.v7.preference.PreferenceFragment.DIALOG";

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        if (preference instanceof NumberPickerPreference) {
            // Inherit the same behaviour as parent
            if (getFragmentManager().findFragmentByTag(DIALOG_FRAGMENT_TAG) != null) {
                return;
            }
            final DialogFragment fragment = NumberPickerPreferenceDialogFragmentCompat.newInstance(preference.getKey());
            fragment.setTargetFragment(this, 0);
            fragment.show(getFragmentManager(), DIALOG_FRAGMENT_TAG);
        } else {
            super.onDisplayPreferenceDialog(preference);
        }
    }

}
