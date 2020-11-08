package com.james.arithmetic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ArithmeticFragment extends Fragment {

    public static final String TAG = ArithmeticFragment.class.getSimpleName();
    public static final String KEY_ANSWER_VISIBLE = "answer_visible";

    public enum ProblemType {ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION}

    private ProblemType selectedProblemType = ProblemType.ADDITION;
    private TextView answer;
    private Button showAnswerButton;
    private boolean answerVisible = false;

    public static ArithmeticFragment newInstance() {
        return new ArithmeticFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save whether the answer TextView is visible or not
        outState.putBoolean(KEY_ANSWER_VISIBLE, answerVisible);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.arithmetic_fragment, container, false);

        if (savedInstanceState != null) {
            answerVisible = savedInstanceState.getBoolean(KEY_ANSWER_VISIBLE);
        }
        answer = view.findViewById(R.id.answer);
        answer.setVisibility(answerVisible ? View.VISIBLE : View.GONE);

        Button newQuestionButton = view.findViewById(R.id.new_question);
        newQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // generate a new question
                Log.d(TAG, "new question button clicked");
            }
        });

        showAnswerButton = view.findViewById(R.id.show_hide_answer);
        updateShowAnswerButtonText();
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show/hide the answer
                Log.d(TAG, "show/hide answer button clicked");
                answer.setVisibility(answerVisible ? View.GONE : View.VISIBLE);
                answerVisible = !answerVisible;
                updateShowAnswerButtonText();
            }
        });

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);
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

    // helper methods
    private void updateShowAnswerButtonText() {
        showAnswerButton.setText(answerVisible ? R.string.hide_answer : R.string.show_answer);
    }
}

