package com.james.arithmetic;

import androidx.fragment.app.Fragment;

public class SettingsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SettingsFragment();
    }
}
