package com.james.arithmetic;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class SettingsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SettingsFragment.newInstance();
    }
}
