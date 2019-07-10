package com.example.a3130project.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.a3130project.R;


public class CalendarFragment extends Fragment
{
	private static final String       TAG = "CalendarActivity";
	private              CalendarView mCalendarView;



	@Override
	public void onViewCreated(View view,Bundle savedInstanceState){
		super.onViewCreated(view,savedInstanceState);
		mCalendarView = (CalendarView) getActivity().findViewById(R.id.calendarView);
		mCalendarView.setOnDateChangeListener(new dayChange());
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		// Defines the xml file for the fragment
		return inflater.inflate(R.layout.activity_calendar, parent, false);
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

			//Intent intent = new Intent(CalendarFragment.this, dosageActivity.class);
			//intent.putExtra("date", date);
			//startActivity(intent);


		}

	}

}

