package ru.profitsw2000.calculatorsw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String EXT_LAUNCHER = "MyLauncher"    ;
    Button button ;
    TextView textView   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button)  ;
        textView = findViewById(R.id.text1) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("calculatorsw://intent") ;
                Intent runIntent = new Intent(Intent.ACTION_VIEW, uri)  ;
                ActivityInfo activityInfo = runIntent.resolveActivityInfo(getPackageManager(), runIntent.getFlags())    ;
                if (activityInfo != null) {
                    startActivity(runIntent);
                }
                else {
                    textView.setText("Calculator was not found.");
                }
            }
        });
    }
}