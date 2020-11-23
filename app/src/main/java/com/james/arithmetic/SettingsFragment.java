package com.james.arithmetic;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends PreferenceFragmentCompat {

    public static final String TAG = SettingsFragment.class.getSimpleName();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        Preference resetDefaultsBtn = findPreference(getString(R.string.key_reset_default_settings));
        resetDefaultsBtn.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                // clear all of the preferences in SharedPreferences
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                preferences.edit().clear().commit();

                // manually refresh/reload the preferences that are displayed on the screen since
                // they won't be automatically refreshed otherwise.
                //PreferenceManager.setDefaultValues(getActivity(), R.xml.preferences, true);
                // see https://stackoverflow.com/questions/46655743/android-restore-applications-default-preferences
                getPreferenceScreen().removeAll();
                onCreatePreferences(null,null); // or onCreate(null) in your code
                return true;
            }
        });

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

            /*preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newVal) {
                    final String value = (String) newVal;
                    EditTextPreference etp = (EditTextPreference) preference;
                    etp.setText(value);
                    return true;
                }
            });*/
        }
    }

    private class NumberInputPreference implements EditTextPreference.OnBindEditTextListener {
        @Override
        public void onBindEditText(EditText editText) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }
}
