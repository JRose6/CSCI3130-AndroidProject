package com.example.a3130project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;

public class calendarActivity extends AppCompatActivity
{
	private  static final String TAG = "CalendarActivity";
	private CalendarView mCalendarView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
		//'button'
		mCalendarView = (CalendarView) findViewById(R.id.calendarView);
		mCalendarView.setOnDateChangeListener(new dayChange());

	}
	public class dayChange implements CalendarView.OnDateChangeListener
	{
		@Override
		public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
			String date = year + "/" + (month+1) + "/"+ dayOfMonth ;
			Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);

			Intent intent = new Intent(calendarActivity.this, dosageActivity.class);
			intent.putExtra("date",date);
			startActivity(intent);


		}
	}
}

