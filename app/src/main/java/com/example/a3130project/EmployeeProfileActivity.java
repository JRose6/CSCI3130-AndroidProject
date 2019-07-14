package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.a3130project.Helpers.ToolBarCreator;
import com.example.a3130project.model.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;


public class EmployeeProfileActivity extends AppCompatActivity
{
	private EditText medName, normName, mainDiseases, manufact, sideEff;
	private Button addMedi;



	private FirebaseFirestore database;
	private Intent intent;
	private Profile profile;
	private DocumentReference profileRef;
	private FirebaseAuth mAuth;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_profile);

		medName = findViewById(R.id.genNameEmp);
		normName = findViewById(R.id.nameEmp);
		mainDiseases = findViewById(R.id.mainDiseasesEmp);
		manufact = findViewById(R.id.manuEmp);
		sideEff = findViewById(R.id.sideEffectsEmp);
		addMedi = findViewById(R.id.addMedEmp);


		String mainDiseases1 = mainDiseases.getText().toString();
		String diseases[] = mainDiseases1.split(",");



		database = FirebaseFirestore.getInstance();

		mAuth = FirebaseAuth.getInstance();

		intent = getIntent();
		profile = (Profile) intent.getSerializableExtra("profile");


		//ToolBarCreator.createBottomNav(this);

		//ToolBarCreator.createToolbar(this, true, false);

		addMedi.setOnClickListener(new OnClicker());

		String TEST = Arrays.toString(diseases);
		Toast.makeText(this, TEST, Toast.LENGTH_LONG).show();

	}

	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			String mainDiseases1 = mainDiseases.getText().toString();
			String diseases[] = mainDiseases1.split(",");
			String TEST = Arrays.toString(diseases);
			Toast.makeText(EmployeeProfileActivity.this, TEST, Toast.LENGTH_SHORT).show();
		}
	}
}
