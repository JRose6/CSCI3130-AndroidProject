package com.example.a3130project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.google.type.DayOfWeek;

import com.google.type.Date;

import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity {

    private static final String TAG = "ScheduleActivity";

    private TextView thedate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosage);
        //thedate = (TextView) findViewById(R.id.date);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch(day){
            case Calendar.SUNDAY:
                thedate.setText("Sunday");
                break;
            case Calendar.MONDAY:
                thedate.setText("Monday");
                break;
            case Calendar.TUESDAY:
                thedate.setText("Tuesday");
                break;
            case Calendar.WEDNESDAY:
                thedate.setText("Wednesday");
                break;
            case Calendar.THURSDAY:
                thedate.setText("Thursday");
                break;
            case Calendar.FRIDAY  :
                thedate.setText("Friday");
                break;
            case Calendar.SATURDAY:
                thedate.setText("Saturday");
                break;

        }
//        Intent incoming = getIntent();
//        String date = incoming.getStringExtra("date");
////        String dow = incoming.getStringExtra("day");



        ;
    }
}