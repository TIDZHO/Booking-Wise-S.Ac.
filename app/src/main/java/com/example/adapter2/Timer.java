package com.example.adapter2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Timer extends AppCompatActivity {

    private TextView textViewTime;
    private TimePicker timePicker;
    DatePicker dt;
    ImageView btn1;
    Button timeButton;
    private boolean is24HView = true;
    public static EditText kol;


    public static String timer;
    String getTimer(){return timer;}
    public static String calendar1;
    String getCalendar(){return calendar1;}
    EditText getKol(){return kol;}




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        kol = (EditText) findViewById(R.id.people);
        dt = (DatePicker) findViewById(R.id.datePicker);
        this.textViewTime = (TextView) this.findViewById(R.id.textView3);
        this.timePicker = (TimePicker) this.findViewById(R.id.timePicker);
        this.timePicker.setIs24HourView(this.is24HView);
        btn1 = findViewById(R.id.btn1);
        kol.getText().toString();


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Timer.this, Done.class);
                startActivity(intent);finish();
            }
        });

        Timer();
        Date();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void Date() {
        this.dt.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                day = dt.getDayOfMonth();
                month = dt.getMonth();
                year =  dt.getYear();

                calendar1 = String.valueOf(day + " " + month + " " + year);
                System.out.println(calendar1);
            }
        });

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
