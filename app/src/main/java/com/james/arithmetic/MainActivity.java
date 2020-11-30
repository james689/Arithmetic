package com.james.arithmetic;

import androidx.fragment.app.Fragment;

// MainActivity is the entry point into the app.
public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return ArithmeticFragment.newInstance();
    }
}