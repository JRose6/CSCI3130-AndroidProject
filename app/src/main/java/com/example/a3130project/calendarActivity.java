package com.example.a3130project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;

public class calendarActivity extends AppCompatActivity
{
	private  static final String TAG = "CalendarActivity";
	private CalendarView mCalendarView;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
		mCalendarView = (CalendarView) findViewById(R.id.calendarView);
		mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
			@Override
			public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
				String date = year + "/" + month + "/"+ dayOfMonth ;
				Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
				Intent intent = new Intent(calendarActivity.this, MainActivity.class);
				intent.putExtra("date",date);
				startActivity(intent);

			}
		});
	}
}

