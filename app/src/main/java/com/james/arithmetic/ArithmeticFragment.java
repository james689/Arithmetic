package com.james.arithmetic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ArithmeticFragment extends Fragment {

    private static final String TAG = ArithmeticFragment.class.getSimpleName();
    public enum ProblemType {ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION}

    private ProblemType selectedProblemType = ProblemType.ADDITION;

    public static ArithmeticFragment newInstance() {
        return new ArithmeticFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.arithmetic_fragment, container, false);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemHorizontalTranslationEnabled(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_addition:
                        Log.d(TAG, "addition selected");
                        selectedProblemType = ProblemType.ADDITION;
                        return true;
                    case R.id.action_subtraction:
                        Log.d(TAG, "subtraction selected");
                        selectedProblemType = ProblemType.SUBTRACTION;
                        return true;
                    case R.id.action_multiplication:
                        Log.d(TAG, "multiplication selected");
                        selectedProblemType = ProblemType.MULTIPLICATION;
                        return true;
                    case R.id.action_division:
                        Log.d(TAG, "division selected");
                        selectedProblemType = ProblemType.DIVISION;
                        return true;
                    default:
                        return false;
                }
            }
        });

        return view;
    }
}

