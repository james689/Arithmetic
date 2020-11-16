package com.james.arithmetic;

import androidx.fragment.app.Fragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return ArithmeticFragment.newInstance();
    }
}