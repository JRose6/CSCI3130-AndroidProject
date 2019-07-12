package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.a3130project.model.Medication;

public class MedicationDetails extends AppCompatActivity
{
	private Intent     intent;
	private Medication medication;

	private TextView viewName;
	private TextView viewGenName;
	private TextView viewManufacturer;
	private TextView viewSideEffects;
	private TextView viewMainDiseases;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medication_details);

		viewName = findViewById(R.id.viewMedNameMedDetails);
		viewGenName = findViewById(R.id.viewGenNameMedDetails);
		viewManufacturer = findViewById(R.id.viewManufacturer);
		viewSideEffects = findViewById(R.id.viewSideEffects);
		viewMainDiseases = findViewById(R.id.viewMainDiseases);
	}


	@Override
	protected void onStart()
	{
		super.onStart();
		intent = getIntent();
		if ( intent == null )
			finish();

		medication = (Medication) intent.getSerializableExtra("medication");
		if ( medication == null )
			finish();

		viewName.setText("Name:\n" + medication.name);
		viewGenName.setText("General Name:\n" + medication.genName);
		viewManufacturer.setText("Manufacturer:\n" + medication.manufacturer);
		viewSideEffects.setText("Side Effects:\n" + medication.sideEffects.toString());
		viewMainDiseases.setText("Main Diseases:\n" + medication.mainDiseases.toString());
	}
}
