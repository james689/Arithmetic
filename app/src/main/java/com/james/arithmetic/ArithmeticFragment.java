package com.james.arithmetic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ArithmeticFragment extends Fragment {

    public static ArithmeticFragment newInstance() {
        return new ArithmeticFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.arithmetic_fragment, container, false);

        return view;
    }
}

