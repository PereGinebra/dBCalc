package com.example.headphonedb;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    TextView MaxOutdBox, outdBox;
    EditText inEffBox, inVoltBox, inResBox;
    Button calcButton;
    private AudioManager am;
    private float max_db;

    public void calculate (View view) {
        int eff, res;
        double volt;

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

            max_db = eff+10*(float)Math.log10(((volt*volt)/res)*1000);
            MaxOutdBox.setTextColor(Color.BLACK);
            MaxOutdBox.setText(String.valueOf(max_db)+" dB");

            int stream_type = AudioManager.STREAM_MUSIC;
            float db = am.getStreamVolumeDb(stream_type, am.getStreamVolume(stream_type),  AudioDeviceInfo.TYPE_WIRED_HEADPHONES);
            float db_out = max_db + db;
            if(db_out < 0) db_out = 0;
            outdBox.setText(String.valueOf(db_out)+ " dB");
        }
        else {
            MaxOutdBox.setText("Some values are missing");
            MaxOutdBox.setTextColor(Color.rgb(210,0,0));
            outdBox.setText("Current dB max out");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaxOutdBox = findViewById(R.id.maxOutdB);
        outdBox = findViewById(R.id.outdB);
        inEffBox = findViewById(R.id.inEff);
        inVoltBox = findViewById(R.id.inVolt);
        inResBox = findViewById(R.id.inRes);
        calcButton = findViewById(R.id.calc);

        calcButton.setText("calcdB");

        am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

    }
}
