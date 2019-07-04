package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.DateFormat;


import android.widget.Button;

import android.os.Bundle;
import android.view.Menu;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3130project.model.Medication;

import java.util.ArrayList;
import java.util.Calendar;


public class dosageActivity extends AppCompatActivity
{

	private ListView listviewMed;

	private static final String TAG = "dosageActivity";
	private Button back;

	Date DOW1;

	private Intent intent;

	private TextView dow;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dosage);
		ToolBarCreator.createToolbar(this);



		//Toast.makeText(this, dow1, Toast.LENGTH_SHORT).show();
		/*
		switch(dow1) {
			case 1:
				dow.setText("Sunday");
				break;
			case 2:
				dow.setText("Monday");
				break;
			case 3:
				dow.setText("Tuesday");
				break;
			case 4:
				dow.setText("Wednesday");
				break;
			case 5:
				dow.setText("Thursday");
				break;
			case 6:
				dow.setText("Friday");
				break;
			case 7:
				dow.setText("Saturday");
				break;
			default:
				//
				break;
		}*/
	}

	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{

			Intent intent = new Intent(dosageActivity.this, calendarActivity.class);
			startActivity(intent);
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this, menu);
	}
}
