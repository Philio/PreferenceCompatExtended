package android.support.v7.preference;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TimePicker;

public class TimePickerPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat implements TimePickerDialog.OnTimeSetListener {

    public static TimePickerPreferenceDialogFragmentCompat newInstance(String key) {
        TimePickerPreferenceDialogFragmentCompat fragment = new TimePickerPreferenceDialogFragmentCompat();
        Bundle args = new Bundle(1);
        args.putString(ARG_KEY, key);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        TimePickerPreference preference = (TimePickerPreference) getPreference();
        return new TimePickerDialog(getActivity(), this, preference.getHours(), preference.getMins(), preference.isDialog24Hours());
    }

    @Override public void onDialogClosed(boolean positiveResult) {
        // Not needed, as handled by onTimeSet
    }

    @Override public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TimePickerPreference preference = (TimePickerPreference) getPreference();
        if (preference.callChangeListener(TimePickerPreference.calculateValue(hourOfDay, minute))) {
            preference.setValue(hourOfDay, minute);
        }
    }

}
