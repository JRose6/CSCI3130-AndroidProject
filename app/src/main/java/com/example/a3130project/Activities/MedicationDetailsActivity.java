package com.example.a3130project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.a3130project.R;
import com.example.a3130project.model.Medication;
import com.google.firebase.firestore.FirebaseFirestore;

public class MedicationDetailsActivity extends AppCompatActivity
{
	private Intent            intent;
	private Medication        medication;

	private TextView viewName;
	private TextView viewGenName;
	private TextView viewManufacturer;
	private TextView viewSideEffects;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medication_details);

		viewName = findViewById(R.id.viewMedNameMedDetails);
		viewGenName = findViewById(R.id.viewGenNameMedDetails);
		viewManufacturer = findViewById(R.id.viewManufacturer);
		viewSideEffects = findViewById(R.id.viewSideEffects);
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

		viewName.setText(medication.name);
		viewGenName.setText(medication.genName);
		viewManufacturer.setText(medication.manufacturer);
		viewSideEffects.setText(medication.sideEffects.toString());
	}
}
