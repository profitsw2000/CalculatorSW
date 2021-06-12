package ru.profitsw2000.calculatorsw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static String keyCalculator = "AAAAA"    ;
    private Button one  ;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button zero;
    private Button point;
    private Button divide;
    private Button multiple;
    private Button minus;
    private Button plus;
    private Button clear;
    private Button backspace;
    private Button sqrt;
    private Button equal;
    private TextView enter_field    ;
    private Calculator calculator = new Calculator()   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        buttonsListen();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(keyCalculator, calculator);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = savedInstanceState.getParcelable(keyCalculator) ;
        enter_field.setText(calculator.getOutputText());
    }

    private void findViews(){
        zero = findViewById(R.id.button_0)  ;
        one = findViewById(R.id.button_1)   ;
        two = findViewById(R.id.button_2)   ;
        three = findViewById(R.id.button_3) ;
        four = findViewById(R.id.button_4)  ;
        five = findViewById(R.id.button_5)  ;
        six = findViewById(R.id.button_6)   ;
        seven = findViewById(R.id.button_7) ;
        eight = findViewById(R.id.button_8) ;
        nine = findViewById(R.id.button_9)  ;
        point = findViewById(R.id.button_point) ;
        divide = findViewById(R.id.button_divide)   ;
        multiple = findViewById(R.id.button_multiple)   ;
        minus = findViewById(R.id.button_minus) ;
        plus = findViewById(R.id.button_plus)   ;
        clear = findViewById(R.id.button_clear) ;
        backspace = findViewById(R.id.button_backspace) ;
        sqrt = findViewById(R.id.button_square_root)    ;
        equal = findViewById(R.id.button_equal) ;
        enter_field = findViewById(R.id.enter_field)    ;
    }

    private void buttonsListen() {
        setListener(one, calculator);
        setListener(two, calculator);
        setListener(three, calculator);
        setListener(four, calculator);
        setListener(five, calculator);
        setListener(six, calculator);
        setListener(seven, calculator);
        setListener(eight, calculator);
        setListener(nine, calculator);
        setListener(zero, calculator);
        setListener(point, calculator);
        setListener(divide, calculator);
        setListener(multiple, calculator);
        setListener(minus, calculator);
        setListener(plus, calculator);
        setListener(clear, calculator);
        setListener(backspace, calculator);
        setListener(sqrt, calculator);
        setListener(equal, calculator);
    }

    private void setListener (Button button, Calculator calculator)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.getButton(button.getText().toString());
                enter_field.setText(calculator.getOutputText());
            }
        });
    }

}