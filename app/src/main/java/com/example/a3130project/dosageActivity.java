package com.example.a3130project;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;



import android.widget.ListView;
import android.widget.TextView;

import com.example.a3130project.model.Medication;

import java.util.ArrayList;
import java.util.Calendar;


public class dosageActivity extends AppCompatActivity
{

	private ListView listviewMed;

	private static final String TAG = "dosageActivity";

	private TextView dow;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dosage);
		ToolBarCreator.createToolbar(this);
		listviewMed = findViewById(R.id.listViewMed);

		dow = findViewById(R.id.textViewDOW);
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);

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


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this,menu);
	}
}
