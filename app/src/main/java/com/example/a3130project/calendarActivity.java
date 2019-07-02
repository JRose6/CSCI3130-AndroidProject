package com.example.a3130project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
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
		ToolBarCreator.createToolbar(this);
		mCalendarView = (CalendarView) findViewById(R.id.calendarView);
		mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
			@Override
			public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
				String date = year + "/" + (month+1) + "/"+ dayOfMonth ;
				Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
				Intent intent = new Intent(calendarActivity.this, dosageActivity.class);
				intent.putExtra("date",date);
				startActivity(intent);


			}
		});


//		Button addEvent = (Button) findViewById(R.id.buttonAddEvent);
//		addEvent.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Calendar calendarEvent = Calendar.getInstance();
//				Intent intent = new Intent(Intent.ACTION_EDIT);
//				intent.setType("vnd.android.cursor.item/event");
//				intent.putExtra("beginTime", calendarEvent.getTimeInMillis());
//				intent.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
//				intent.putExtra("title", "Sample Event");
//				intent.putExtra("allDay", true);
//				intent.putExtra("rule", "FREQ=YEARLY");
//				startActivity(intent);
//
//			;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this,menu);
	}
}

