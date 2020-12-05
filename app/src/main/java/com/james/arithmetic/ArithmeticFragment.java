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
import com.james.arithmetic.question.ArithmeticQuestion;
import com.james.arithmetic.question.ArithmeticQuestionFactory;

public class ArithmeticFragment extends Fragment {

    public static final String TAG = ArithmeticFragment.class.getSimpleName();
    // keys used for saving state across device configuration changes such as screen rotation
    public static final String KEY_ANSWER_VISIBLE = "answer_visible";
    public static final String KEY_PROBLEM_TYPE = "problem_type";
    public static final String KEY_CURRENT_QUESTION = "current_question";

    public enum ProblemType {ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION}
    public static final ProblemType defaultProblemType = ProblemType.ADDITION;

    private TextView questionTextView;
    private TextView answerTextView;
    private Button showHideAnswerButton;

    private ProblemType currentProblemType = defaultProblemType;
    private boolean answerVisible = false;
    private ArithmeticQuestion currentQuestion = null;

    public static ArithmeticFragment newInstance() {
        return new ArithmeticFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState called");
        outState.putBoolean(KEY_ANSWER_VISIBLE, answerVisible);
        outState.putSerializable(KEY_PROBLEM_TYPE, currentProblemType);
        outState.putSerializable(KEY_CURRENT_QUESTION, currentQuestion);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        if (savedInstanceState != null) {
            Log.d(TAG, "saved instance state in onCreate() not null");
            answerVisible = savedInstanceState.getBoolean(KEY_ANSWER_VISIBLE);
            currentProblemType = (ProblemType) savedInstanceState.getSerializable(KEY_PROBLEM_TYPE);
            currentQuestion = (ArithmeticQuestion) savedInstanceState.getSerializable(KEY_CURRENT_QUESTION);
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
        Log.d(TAG, "onCreateView called");
        View view = inflater.inflate(R.layout.arithmetic_fragment, container, false);

        questionTextView = view.findViewById(R.id.question);
        answerTextView = view.findViewById(R.id.answer);

        if (currentQuestion == null) {
            // create a new question to display
            newQuestion();
        } else {
            updateQuestionAndAnswerViews();
        }

        answerTextView.setVisibility(answerVisible ? View.VISIBLE : View.GONE);

        Button newQuestionButton = view.findViewById(R.id.new_question);
        newQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "new question button clicked, problem type = " + currentProblemType);
                newQuestion();
            }
        });

        showHideAnswerButton = view.findViewById(R.id.show_hide_answer);
        updateShowAnswerButtonText();
        showHideAnswerButton.setOnClickListener(new View.OnClickListener() {
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
                        currentProblemType = ProblemType.ADDITION;
                        return true;
                    case R.id.action_subtraction:
                        Log.d(TAG, "subtraction selected");
                        currentProblemType = ProblemType.SUBTRACTION;
                        return true;
                    case R.id.action_multiplication:
                        Log.d(TAG, "multiplication selected");
                        currentProblemType = ProblemType.MULTIPLICATION;
                        return true;
                    case R.id.action_division:
                        Log.d(TAG, "division selected");
                        currentProblemType = ProblemType.DIVISION;
                        return true;
                    default:
                        return false;
                }
            }
        });

        // set selected item in bottom navigation view depending on currently selected problem type
        switch (currentProblemType) {
            case ADDITION:
                bottomNavigationView.setSelectedItemId(R.id.action_addition);
                break;
            case SUBTRACTION:
                bottomNavigationView.setSelectedItemId(R.id.action_subtraction);
                break;
            case MULTIPLICATION:
                bottomNavigationView.setSelectedItemId(R.id.action_multiplication);
                break;
            case DIVISION:
                bottomNavigationView.setSelectedItemId(R.id.action_division);
                break;
        }

        return view;
    }

    ////////////////////// helper methods ///////////////////////////////////////

    private void updateShowAnswerButtonText() {
        showHideAnswerButton.setText(answerVisible ? R.string.hide_answer : R.string.show_answer);
    }

    private void newQuestion() {
        currentQuestion = createQuestion();
        updateQuestionAndAnswerViews();
        //questionTextView.setText(currentQuestion.toString());
        //answerTextView.setText("Answer: " + currentQuestion.getAnswer());
    }

    private void updateQuestionAndAnswerViews() {
        questionTextView.setText(currentQuestion.toString());
        answerTextView.setText("Answer: " + currentQuestion.getAnswer());
    }

    // Creates and returns a new Question. The specific type of Question that is created depends upon the
    // currentProblemType. A Question is configured using the app's shared preferences.
    private ArithmeticQuestion createQuestion() {

        switch (currentProblemType) {
            case ADDITION:
                int num1IntDigits = getNumDigits(R.string.key_addition_num1_int_digits, R.string.addition_num1_int_digits_default_value);
                int num1FractionalDigits = getNumDigits(R.string.key_addition_num1_fractional_digits, R.string.addition_num1_fractional_digits_default_value);
                int num2IntDigits = getNumDigits(R.string.key_addition_num2_int_digits, R.string.addition_num2_int_digits_default_value);
                int num2FractionalDigits = getNumDigits(R.string.key_addition_num2_fractional_digits, R.string.addition_num2_fractional_digits_default_value);
                return ArithmeticQuestionFactory.createAdditionQuestion(num1IntDigits,num1FractionalDigits,
                        num2IntDigits,num2FractionalDigits);
            case SUBTRACTION:
                int minuendIntDigits = getNumDigits(R.string.key_subtraction_minuend_int_digits, R.string.subtraction_minuend_int_digits_default_value);
                int minuendFractionalDigits = getNumDigits(R.string.key_subtraction_minuend_fractional_digits, R.string.subtraction_minuend_fractional_digits_default_value);
                int subtrahendIntDigits = getNumDigits(R.string.key_subtraction_subtrahend_int_digits, R.string.subtraction_subtrahend_int_digits_default_value);
                int subtrahendFractionalDigits = getNumDigits(R.string.key_subtraction_subtrahend_fractional_digits, R.string.subtraction_subtrahend_fractional_digits_default_value);
                return ArithmeticQuestionFactory.createSubtractionQuestion(minuendIntDigits, minuendFractionalDigits,
                        subtrahendIntDigits, subtrahendFractionalDigits);
            case MULTIPLICATION:
                int multiplicationNum1IntDigits = getNumDigits(R.string.key_multiplication_num1_int_digits, R.string.multiplication_num1_int_digits_default_value);
                int multiplicationNum1FractionalDigits = getNumDigits(R.string.key_multiplication_num1_fractional_digits, R.string.multiplication_num1_fractional_digits_default_value);
                int multiplicationNum2IntDigits = getNumDigits(R.string.key_multiplication_num2_int_digits, R.string.multiplication_num2_int_digits_default_value);
                int multiplicationNum2FractionalDigits = getNumDigits(R.string.key_multiplication_num2_fractional_digits, R.string.multiplication_num2_fractional_digits_default_value);
                return ArithmeticQuestionFactory.createMultiplicationQuestion(multiplicationNum1IntDigits, multiplicationNum1FractionalDigits,
                        multiplicationNum2IntDigits, multiplicationNum2FractionalDigits);
            case DIVISION:
                int dividendIntDigits = getNumDigits(R.string.key_division_dividend_int_digits, R.string.division_dividend_int_digits_default_value);
                int dividendFractionalDigits = getNumDigits(R.string.key_division_dividend_fractional_digits, R.string.division_dividend_fractional_digits_default_value);
                int divisorIntDigits = getNumDigits(R.string.key_division_divisor_int_digits, R.string.division_divisor_int_digits_default_value);
                int divisorFractionalDigits = getNumDigits(R.string.key_division_divisor_fractional_digits, R.string.division_dividend_fractional_digits_default_value);
                return ArithmeticQuestionFactory.createDivisionQuestion(dividendIntDigits, dividendFractionalDigits,
                        divisorIntDigits, divisorFractionalDigits);
            default: return null;
        }
    }

    // helper method that returns the number of digits for a preference
    private int getNumDigits(int resourceId, int defaultValueResourceId) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String key = getString(resourceId);
        String defaultValue = getString(defaultValueResourceId);
        String preference = sharedPreferences.getString(key, defaultValue);
        return Integer.parseInt(preference);
    }
}

