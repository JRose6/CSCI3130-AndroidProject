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
		listviewMed = findViewById(R.id.listViewMed);

		back = findViewById(R.id.DosageBACKButton);
		back.setOnClickListener(new OnClicker());

		intent = getIntent();

		String date2 = (String) intent.getSerializableExtra("date");

		dow = findViewById(R.id.textViewDOW);




		ArrayList<Medication> Medi = new ArrayList<>();

		Medication med1 = new Medication("1", "Adderall", "4 doses a day");
		Medication med2 = new Medication("2", "blah", "4 doses a day");
		Medication med3 = new Medication("3", "sos", "3 doses a day");
		Medication med4 = new Medication("4", "Afsf", "1 doses a day");
		Medication med5 = new Medication("5", "akajd", "12 doses a day");

		Medi.add(med1);
		Medi.add(med2);
		Medi.add(med3);
		Medi.add(med4);
		Medi.add(med5);

		PrescriptionAdapter adapter = new PrescriptionAdapter(dosageActivity.this, R.layout.adapter_view_dosages, Medi);
		listviewMed.setAdapter(adapter);


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
		return ToolBarCreator.createMenu(this,menu);
	}
}
