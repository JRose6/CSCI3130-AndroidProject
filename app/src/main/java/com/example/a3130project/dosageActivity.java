package com.example.a3130project;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;

import com.example.a3130project.model.Medication;

import java.util.ArrayList;


public class dosageActivity extends AppCompatActivity
{

	private ListView listviewMed;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dosage);
		ToolBarCreator.createToolbar(this);
		listviewMed = findViewById(R.id.listViewMed);

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

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this,menu);
	}
}
