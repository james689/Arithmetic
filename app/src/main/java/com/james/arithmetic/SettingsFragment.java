package com.james.arithmetic;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
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
    }
}
