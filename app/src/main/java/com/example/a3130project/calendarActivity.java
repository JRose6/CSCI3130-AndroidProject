package com.example.a3130project;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.widget.CalendarView;



public class calendarActivity extends AppCompatActivity
{
	private static final String       TAG = "CalendarActivity";
	private              CalendarView mCalendarView;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
		ToolBarCreator.createBottomNav(this);
		ToolBarCreator.createToolbar(this);
		//'button'
		mCalendarView = (CalendarView) findViewById(R.id.calendarView);
		mCalendarView.setOnDateChangeListener(new dayChange());


	}


	public class dayChange implements CalendarView.OnDateChangeListener
	{
		@Override
		public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth)
		{
			month = month + 1;
			String DOM = "";
			String newMonth = "";
			if(dayOfMonth < 10)
			{
				 DOM = "0" + dayOfMonth;
			}
			else
				DOM = "" + dayOfMonth;
			if(month < 10)
			{
				newMonth = "0" + month;
			}
			else
			    newMonth = "" + month;
			String date = DOM + "-" + newMonth + "-" + year;
			Log.d(TAG, "onSelectedDayChange: dd-MM-yyyy" + date);

			Intent intent = new Intent(calendarActivity.this, dosageActivity.class);
			intent.putExtra("date", date);
			startActivity(intent);


		}

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this, menu);
	}
}

