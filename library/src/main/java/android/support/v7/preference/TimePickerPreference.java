package android.support.v7.preference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import me.philio.preferencecompatextended.R;

import static android.text.format.DateFormat.getTimeFormat;
import static android.text.format.DateFormat.is24HourFormat;

public class TimePickerPreference extends DialogPreference {

    public static final int TIME_FORMAT_INHERIT = 0;
    public static final int TIME_FORMAT_12HOURS = 1;
    public static final int TIME_FORMAT_24HOURS = 2;
    public static final int TIME_FORMAT_CUSTOM = 3;

    private boolean timeAsSummary;
    private int timeFormat;
    private String timeFormatString;
    private int defaultHours;
    private int defaultMins;
    private int dialogTimeFormat;
    private int value;

    public TimePickerPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimePickerPreference, defStyleAttr, defStyleRes);
        timeAsSummary = typedArray.getBoolean(R.styleable.TimePickerPreference_timeAsSummary, true);
        timeFormat = typedArray.getInt(R.styleable.TimePickerPreference_timeFormat, 0);
        timeFormatString = typedArray.getString(R.styleable.TimePickerPreference_timeFormatString);
        if (timeFormatString != null) {
            timeFormat = TIME_FORMAT_CUSTOM;
        }
        defaultHours = typedArray.getInt(R.styleable.TimePickerPreference_defaultHours, -1);
        defaultMins = typedArray.getInt(R.styleable.TimePickerPreference_defaultMins, -1);
        if (defaultHours >= 0 && defaultMins >= 0) {
            setDefaultValue(calculateValue(defaultHours, defaultMins));
        }
        dialogTimeFormat = typedArray.getInt(R.styleable.TimePickerPreference_dialogTimeFormat, 0);
        typedArray.recycle();
    }

    public TimePickerPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TimePickerPreference(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.timePickerPreferenceStyle);
    }

    public TimePickerPreference(Context context) {
        this(context, null);
    }

    public static int calculateValue(int hours, int mins) {
        return (hours * 3600) + (mins * 60);
    }

    public static int calculateHours(int value) {
        return (int) Math.floor(value / 3600);
    }

    public static int calculateMins(int value) {
        return (value % 3600) / 60;
    }

    @SuppressLint("SimpleDateFormat")
    public void setValue(int value) {
        this.value = value;
        if (timeAsSummary) {
            DateFormat dateFormat;
            switch (timeFormat) {
                case TIME_FORMAT_12HOURS:
                    dateFormat = new SimpleDateFormat("h:mm aa");
                    break;
                case TIME_FORMAT_24HOURS:
                    dateFormat = new SimpleDateFormat("HH:mm");
                    break;
                case TIME_FORMAT_CUSTOM:
                    dateFormat = new SimpleDateFormat(timeFormatString);
                    break;
                case TIME_FORMAT_INHERIT:
                default:
                    dateFormat = getTimeFormat(getContext());
                    break;
            }
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = new Date(value * 1000);
            String time = dateFormat.format(date);
            setSummary(time);
        }
        persistInt(value);
    }

    public void setValue(int hours, int mins) {
        setValue(calculateValue(hours, mins));
    }

    public int getValue() {
        return value;
    }

    public int getHours() {
        return calculateHours(value);
    }

    public int getMins() {
        return calculateMins(value);
    }

    public boolean isDialog24Hours() {
        switch (dialogTimeFormat) {
            case TIME_FORMAT_12HOURS:
                return false;
            case TIME_FORMAT_24HOURS:
                return true;
            case TIME_FORMAT_INHERIT:
            default:
                return is24HourFormat(getContext());
        }
    }

    @Override protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getInt(index, defaultHours > -1 && defaultMins > -1 ? (defaultHours * 3600) + (defaultMins * 60) : 0);
    }

    @Override protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        setValue(restorePersistedValue ? getPersistedInt(value) : (Integer) defaultValue);
    }

    @Override protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        if (isPersistent()) {
            return superState;
        }

        final SavedState savedState = new SavedState(superState);
        savedState.timeAsSummary = timeAsSummary;
        savedState.timeFormat = timeFormat;
        savedState.timeFormatString = timeFormatString;
        savedState.defaultHours = defaultHours;
        savedState.defaultMins = defaultMins;
        savedState.dialogTimeFormat = dialogTimeFormat;
        savedState.value = value;
        return savedState;
    }

    @Override protected void onRestoreInstanceState(Parcelable state) {
        if (state == null || !state.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        timeAsSummary = savedState.timeAsSummary;
        timeFormat = savedState.timeFormat;
        timeFormatString = savedState.timeFormatString;
        defaultHours = savedState.defaultHours;
        defaultMins = savedState.defaultMins;
        dialogTimeFormat = savedState.dialogTimeFormat;
        value = savedState.value;
    }

    private static class SavedState extends BaseSavedState {

        private boolean timeAsSummary;
        private int timeFormat;
        private String timeFormatString;
        private int defaultHours;
        private int defaultMins;
        private int dialogTimeFormat;
        private int value;

        @SuppressLint("ParcelClassLoader")
        SavedState(Parcel source) {
            super(source);
            timeAsSummary = (boolean) source.readValue(null);
            timeFormat = source.readInt();
            timeFormatString = source.readString();
            defaultHours = source.readInt();
            defaultMins = source.readInt();
            dialogTimeFormat = source.readInt();
            value = source.readInt();
        }

        SavedState(Parcelable superState) {
            super(superState);
        }

        @Override public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeValue(timeAsSummary);
            dest.writeInt(timeFormat);
            dest.writeString(timeFormatString);
            dest.writeInt(defaultHours);
            dest.writeInt(defaultMins);
            dest.writeInt(dialogTimeFormat);
            dest.writeInt(value);
        }

        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {

                    @Override public SavedState createFromParcel(Parcel source) {
                        return new SavedState(source);
                    }

                    @Override public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }

                };

    }

}
