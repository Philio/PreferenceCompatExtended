package android.support.v7.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;

import me.philio.preferencecompatextended.R;

public class NumberPickerPreference extends DialogPreference {

    private int minValue;
    private int maxValue;
    private boolean valueAsSummary;
    private boolean descendantFocusable;

    private int value;

    public NumberPickerPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberPickerPreference, defStyleAttr, defStyleRes);
        minValue = typedArray.getInt(R.styleable.NumberPickerPreference_minValue, 0);
        maxValue = typedArray.getInt(R.styleable.NumberPickerPreference_maxValue, 0);
        valueAsSummary = typedArray.getBoolean(R.styleable.NumberPickerPreference_valueAsSummary, false);
        descendantFocusable = typedArray.getBoolean(R.styleable.NumberPickerPreference_descendantFocusable, true);
        typedArray.recycle();
    }

    public NumberPickerPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public NumberPickerPreference(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.numberPickerPreferenceStyle);
    }

    public NumberPickerPreference(Context context) {
        this(context, null);
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public boolean isValueAsSummary() {
        return valueAsSummary;
    }

    public boolean isDescendantFocusable() {
        return descendantFocusable;
    }

    public void setValue(int value) {
        this.value = value;
        if (isValueAsSummary()) {
            setSummary(Integer.toString(value));
        }
        persistInt(value);
    }

    public int getValue() {
        return value;
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getInt(index, minValue > 0 ? minValue : 0);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        setValue(restorePersistedValue ? getPersistedInt(value) : (Integer) defaultValue);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        if (isPersistent()) {
            return superState;
        }

        final SavedState savedState = new SavedState(superState);
        savedState.minValue = minValue;
        savedState.maxValue = maxValue;
        savedState.valueAsSummary = valueAsSummary;
        savedState.descendantFocusable = descendantFocusable;
        savedState.value = value;
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state == null || !state.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        minValue = savedState.minValue;
        maxValue = savedState.maxValue;
        valueAsSummary = savedState.valueAsSummary;
        descendantFocusable = savedState.descendantFocusable;
        value = savedState.value;
    }

    private static class SavedState extends BaseSavedState {

        private int minValue;
        private int maxValue;
        private boolean valueAsSummary;
        private boolean descendantFocusable;
        private int value;

        public SavedState(Parcel source) {
            super(source);
            minValue = source.readInt();
            maxValue = source.readInt();
            valueAsSummary = (boolean) source.readValue(null);
            descendantFocusable = (boolean) source.readValue(null);
            value = source.readInt();
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(minValue);
            dest.writeInt(maxValue);
            dest.writeValue(valueAsSummary);
            dest.writeValue(descendantFocusable);
            dest.writeInt(value);
        }

        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {

                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }

                };
    }

}
