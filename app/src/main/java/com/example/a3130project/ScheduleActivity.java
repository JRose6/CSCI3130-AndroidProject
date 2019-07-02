package com.example.a3130project;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;



import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity {

    private static final String TAG = "ScheduleActivity";

    private TextView dow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosage);
        dow = findViewById(R.id.textViewDOW);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch(day){
            case Calendar.SUNDAY:
                dow.setText("Sunday");
                break;
            case Calendar.MONDAY:
                dow.setText("Monday");
                break;
            case Calendar.TUESDAY:
                dow.setText("Tuesday");
                break;
            case Calendar.WEDNESDAY:
                dow.setText("Wednesday");
                break;
            case Calendar.THURSDAY:
                dow.setText("Thursday");
                break;
            case Calendar.FRIDAY  :
                dow.setText("Friday");
                break;
            case Calendar.SATURDAY:
                dow.setText("Saturday");
                break;

        }

    }
}