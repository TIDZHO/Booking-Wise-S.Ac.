package com.example.adapter2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Timer extends AppCompatActivity {

    private TextView textViewTime;
    private TimePicker timePicker;
    float dtTime;
    ImageView btn1;
    Button timeButton;
    private boolean is24HView = true;


    public static String timer;
    String getTimer(){return timer;}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        this.textViewTime = (TextView) this.findViewById(R.id.textView3);
        this.timePicker = (TimePicker) this.findViewById(R.id.timePicker);
        this.timePicker.setIs24HourView(this.is24HView);
        btn1 = findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Timer.this, Done.class);
                startActivity(intent);finish();


            }
        });

        Timer();




    }

    public void Timer(){
        this.timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                textViewTime.setText(hourOfDay + " " + minute);

                int a =  hourOfDay;
                int b = minute;
                timer = String.valueOf(a + " " + b);
                System.out.println(timer);

            }
        });


    }


}
