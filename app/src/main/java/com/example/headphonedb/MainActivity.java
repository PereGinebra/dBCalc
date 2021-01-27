package com.example.headphonedb;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    TextView outdBox;
    EditText inEffBox, inVoltBox, inResBox;
    Button calcButton;

    public void calculate (View view) {
        int eff, res;
        double volt;
        double db;

        if(!inEffBox.getText().toString().equals(""))
            eff = Integer.parseInt(inEffBox.getText().toString());
        else eff = -1;
        if(!inResBox.getText().toString().equals(""))
            res = Integer.parseInt(inResBox.getText().toString());
        else res = -1;
        if (!inVoltBox.getText().toString().equals(""))
            volt = Double.parseDouble(inVoltBox.getText().toString());
        else volt = -1;

        if(eff != -1 && res != -1 && volt != -1) {
            db = eff+10*Math.log10(((volt*volt)/res)*1000);
            outdBox.setTextColor(Color.BLACK);
            outdBox.setText(String.valueOf(db)+" dB");
        }
        else {
            outdBox.setText("Some values are missing");
            outdBox.setTextColor(Color.rgb(210,0,0));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outdBox = findViewById(R.id.outdB);
        inEffBox = findViewById(R.id.inEff);
        inVoltBox = findViewById(R.id.inVolt);
        inResBox = findViewById(R.id.inRes);
        calcButton = findViewById(R.id.calc);

        calcButton.setText("calcdB");

    }
}
