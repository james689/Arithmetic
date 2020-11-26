package com.james.arithmetic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.james.arithmetic.question.Question;
import com.james.arithmetic.question.QuestionFactory;

public class ArithmeticFragment extends Fragment {

    public static final String TAG = ArithmeticFragment.class.getSimpleName();
    public static final String KEY_ANSWER_VISIBLE = "answer_visible";
    public static final String KEY_PROBLEM_TYPE = "problem_type";
    public static final String KEY_CURRENT_QUESTION = "current_question";

    public enum ProblemType {ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION}

    private ProblemType selectedProblemType = ProblemType.ADDITION;
    private TextView questionTextView;
    private TextView answerTextView;
    private Button showAnswerButton;
    private boolean answerVisible = false;
    private Question currentQuestion = null;

    public static ArithmeticFragment newInstance() {
        return new ArithmeticFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState called");
        outState.putBoolean(KEY_ANSWER_VISIBLE, answerVisible);
        outState.putSerializable(KEY_PROBLEM_TYPE, selectedProblemType);
        outState.putSerializable(KEY_CURRENT_QUESTION, currentQuestion);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        if (savedInstanceState != null) {
            Log.d(TAG, "saved instance state in onCreate() not null");
            answerVisible = savedInstanceState.getBoolean(KEY_ANSWER_VISIBLE);
            selectedProblemType = (ProblemType) savedInstanceState.getSerializable(KEY_PROBLEM_TYPE);
            currentQuestion = (Question) savedInstanceState.getSerializable(KEY_CURRENT_QUESTION);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.arithmetic_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                // launch the settings activity
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.arithmetic_fragment, container, false);
        Log.d(TAG, "onCreateView called");

        answerTextView = view.findViewById(R.id.answer);
        answerTextView.setVisibility(answerVisible ? View.VISIBLE : View.GONE);

        questionTextView = view.findViewById(R.id.question);
        if (currentQuestion == null) {
            questionTextView.setText("1+1");
        } else {
            questionTextView.setText(currentQuestion.toString());
            answerTextView.setText("Answer: " + currentQuestion.getAnswer());
        }

        Button newQuestionButton = view.findViewById(R.id.new_question);
        newQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "new question button clicked, problem type = " + selectedProblemType);
                newQuestion();
            }
        });

        showAnswerButton = view.findViewById(R.id.show_hide_answer);
        updateShowAnswerButtonText();
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show/hide the answer
                Log.d(TAG, "show/hide answer button clicked");
                answerTextView.setVisibility(answerVisible ? View.GONE : View.VISIBLE);
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

    private void newQuestion() {
        currentQuestion = getQuestion();
        questionTextView.setText(currentQuestion.toString());
        answerTextView.setText("Answer: " + currentQuestion.getAnswer());
    }

    // Returns a new Question. The specific type of Question that is returned depends upon the
    // currently selectedProblemType. A Question is configured using the app's shared preferences.
    private Question getQuestion() {

        switch (selectedProblemType) {
            case ADDITION:
                int num1IntDigits = getNumDigits(R.string.key_addition_num1_int_digits);
                int num1FractionalDigits = getNumDigits(R.string.key_addition_num1_fractional_digits);
                int num2IntDigits = getNumDigits(R.string.key_addition_num2_int_digits);
                int num2FractionalDigits = getNumDigits(R.string.key_addition_num2_fractional_digits);
                return QuestionFactory.createAdditionQuestion(num1IntDigits,num1FractionalDigits,
                        num2IntDigits,num2FractionalDigits);
            case SUBTRACTION:
                int minuendIntDigits = getNumDigits(R.string.key_subtraction_minuend_int_digits);
                int minuendFractionalDigits = getNumDigits(R.string.key_subtraction_minuend_fractional_digits);
                int subtrahendIntDigits = getNumDigits(R.string.key_subtraction_subtrahend_int_digits);
                int subtrahendFractionalDigits = getNumDigits(R.string.key_subtraction_subtrahend_fractional_digits);
                return QuestionFactory.createSubtractionQuestion(minuendIntDigits, minuendFractionalDigits,
                        subtrahendIntDigits, subtrahendFractionalDigits);
            case MULTIPLICATION:
                int multiplicationNum1IntDigits = getNumDigits(R.string.key_multiplication_num1_int_digits);
                int multiplicationNum1FractionalDigits = getNumDigits(R.string.key_multiplication_num1_fractional_digits);
                int multiplicationNum2IntDigits = getNumDigits(R.string.key_multiplication_num2_int_digits);
                int multiplicationNum2FractionalDigits = getNumDigits(R.string.key_multiplication_num2_fractional_digits);
                return QuestionFactory.createMultiplicationQuestion(multiplicationNum1IntDigits, multiplicationNum1FractionalDigits,
                        multiplicationNum2IntDigits, multiplicationNum2FractionalDigits);
            case DIVISION:
                int dividendIntDigits = getNumDigits(R.string.key_division_dividend_int_digits);
                int dividendFractionalDigits = getNumDigits(R.string.key_division_dividend_fractional_digits);
                int divisorIntDigits = getNumDigits(R.string.key_division_divisor_int_digits);
                int divisorFractionalDigits = getNumDigits(R.string.key_division_divisor_fractional_digits);
                return QuestionFactory.createDivisionQuestion(dividendIntDigits, dividendFractionalDigits,
                        divisorIntDigits, divisorFractionalDigits);
            default: return null;
        }
    }

    // helper method that returns the number of digits for a preference
    private int getNumDigits(int resourceId) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String key = getString(resourceId);
        String preference = sharedPreferences.getString(key, null);
        return Integer.parseInt(preference);
    }
}

