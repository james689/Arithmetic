package com.james.arithmetic;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends PreferenceFragmentCompat {

    public static final String TAG = SettingsFragment.class.getSimpleName();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        // get hold of all of the EditText preferences
        List<EditTextPreference> editTextPreferences = new ArrayList<>();
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_addition_num1_int_digits)));
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_addition_num1_fractional_digits)));
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_addition_num2_int_digits)));
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_addition_num2_fractional_digits)));
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_subtraction_minuend_int_digits)));
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_subtraction_minuend_fractional_digits)));
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_subtraction_subtrahend_int_digits)));
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_subtraction_subtrahend_fractional_digits)));
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_multiplication_num1_int_digits)));
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_multiplication_num1_fractional_digits)));
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_multiplication_num2_int_digits)));
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_multiplication_num2_fractional_digits)));
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_division_dividend_int_digits)));
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_division_dividend_fractional_digits)));
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_division_divisor_int_digits)));
        editTextPreferences.add((EditTextPreference) findPreference(getString(R.string.key_division_divisor_fractional_digits)));

        // set a number input preference on each of them
        NumberInputPreference numberInputPreference = new NumberInputPreference();
        for (EditTextPreference preference : editTextPreferences) {
            preference.setOnBindEditTextListener(numberInputPreference);
        }
    }

    private class NumberInputPreference implements EditTextPreference.OnBindEditTextListener {
        @Override
        public void onBindEditText(EditText editText) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }
}
