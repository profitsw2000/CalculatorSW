package ru.profitsw2000.calculatorsw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button one, two, three, four, five, six, seven, eight, nine, zero,
            point, divide, multiple, minus, plus, clear, backspace, sqrt, equal ;
    private TextView enter_field    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        buttonsListen();
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
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_field.setText("0");
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_field.setText("1");
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_field.setText("2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_field.setText("3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_field.setText("4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_field.setText("5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_field.setText("6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_field.setText("7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_field.setText("8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_field.setText("9");
            }
        });
    }

}