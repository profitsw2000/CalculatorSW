package ru.profitsw2000.calculatorsw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    private final String day_string = "Day mode"    ;
    private final String night_string = "Night mode"    ;
    private static final String newMode = "NEW_MODE"    ;
    private static final String currentMode = "CURRENT_MODE"    ;

    private Switch mode ;
    private Button update   ;
    private boolean night_mode = true  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        night_mode = getIntent().getExtras().getBoolean(currentMode)    ;
        setContentView(R.layout.activity_settings);
        findViews();
    }

    private void findViews(){
        mode = findViewById(R.id.switch1)   ;
        update = findViewById(R.id.button_update)   ;

        mode.setChecked(night_mode);
        if (night_mode){
            mode.setText(night_string);
        } else {
            mode.setText(day_string);
        }

        mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mode.isChecked()) {
                    night_mode = true  ;
                    mode.setText(night_string);
                } else {
                    night_mode = false  ;
                    mode.setText(day_string);
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateSettings = new Intent(SettingsActivity.this, MainActivity.class)   ;
                updateSettings.putExtra(newMode, night_mode) ;
                startActivity(updateSettings);
            }
        });
    }


}