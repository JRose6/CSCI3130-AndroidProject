package com.example.a3130project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.a3130project.Helpers.ToolBarCreator;
import com.example.a3130project.R;
import com.example.a3130project.model.Medication;

public class MedicationDetailsActivity extends AppCompatActivity
{
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
		ToolBarCreator.createToolbar(this, true, true);
		viewName = findViewById(R.id.viewMedNameMedDetails);
		viewGenName = findViewById(R.id.viewGenNameMedDetails);
		viewManufacturer = findViewById(R.id.viewManufacturer);
		viewSideEffects = findViewById(R.id.viewSideEffects);
		viewMainDiseases = findViewById(R.id.viewMainDiseases);
/*
		intent = getIntent();
		if ( intent == null )
			finish();

		String id = intent.getStringExtra("medId");
		Log.d("MEDID2", id);
		FirebaseFirestore.getInstance().collection("medications")
		                 .document(id).get()
		                 .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
		                 {
			                 @Override
			                 public void onSuccess(DocumentSnapshot documentSnapshot)
			                 {
				                 medication = documentSnapshot.toObject(Medication.class);
				                 viewName.setText(medication.name);
				                 viewGenName.setText(medication.genName);
				                 viewManufacturer.setText(medication.manufacturer);
				                 viewSideEffects.setText(medication.sideEffects.toString());
				                 if ( medication == null )
					                 finish();
			                 }
		                 });
*/
	}


	@Override
	protected void onStart()
	{
		super.onStart();

		medication = (Medication) getIntent().getSerializableExtra("medication");
		if ( medication == null )
		{
			finish();
			return;
		}
		else
		{
			viewName.setText("Name:\n" + medication.name);
			viewGenName.setText("General Name:\n" + medication.genName);
			viewManufacturer.setText("Manufacturer:\n" + medication.manufacturer);
			viewSideEffects.setText("Side Effects:\n" + medication.sideEffects.toString());
			viewMainDiseases.setText("Main Diseases:\n" + medication.mainDiseases.toString());
		}
	}
}
