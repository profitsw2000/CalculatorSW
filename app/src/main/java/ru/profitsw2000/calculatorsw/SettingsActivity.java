package ru.profitsw2000.calculatorsw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    private Switch mode ;
    private boolean day = true  ;
    private final String day_string = "Day mode"    ;
    private final String night_string = "Night mode"    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        findViews();
    }

    private void findViews(){
        mode = findViewById(R.id.switch1)   ;

        mode.setChecked(day);
        if (day){
            mode.setText(day_string);
        } else {
            mode.setText(night_string);
        }

        mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mode.isChecked()) {
                    day = true  ;
                    mode.setText(day_string);
                } else {
                    day = true  ;
                    mode.setText(night_string);
                }
            }
        });
    }


}